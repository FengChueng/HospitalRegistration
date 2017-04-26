package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.constants.AppConstants;

public class DoctorScheduleActivity extends MvpBaseActivity<DoctorScheduleContract.DoctorSchedulePresenter> implements DoctorScheduleContract.DoctorScheduleView{

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    private String doctorId;
    private int page = 0;
    private int size = 30;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            doctorId = (String) params.get(AppConstants.KEY_DOCTOR_ID);
            mPresenter.getDoctorSchedule(doctorId,page,size);
        }else{
            finish();
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar("医生详情");
        //final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.doctor_info_ll);
        AppBarLayout app_bar=(AppBarLayout)findViewById(R.id.appbar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        //linearLayout.setVisibility(View.GONE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                            //linearLayout.setVisibility(View.VISIBLE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_doctor_schedule;
    }

    @Override
    protected DoctorScheduleContract.DoctorSchedulePresenter createPresenter() {
        return new DoctorSchedulePresenterImpl(this);
    }

    @Override
    public void getDoctorScheduleSucc(DoctorBean doctorSchedule) {

    }

    @Override
    public void getDoctorScheduleError(String msg) {

    }

    @Override
    public void noData(String msg) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
