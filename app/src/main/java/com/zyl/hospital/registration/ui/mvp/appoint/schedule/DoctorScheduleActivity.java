package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.adapter.DoctorScheduleAdapter;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.utils.ImageLoader;
import com.zyl.hospital.registration.widget.CircleImageView;

import butterknife.BindView;

public class DoctorScheduleActivity extends MvpBaseActivity<DoctorScheduleContract.DoctorSchedulePresenter> implements DoctorScheduleContract.DoctorScheduleView {

    @BindView(R.id.doctor_portrait)
    CircleImageView doctorPortrait;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.doctor_info_ll)
    LinearLayout doctorInfoLl;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.doctor_name)
    TextView doctorName;
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
            mPresenter.getDoctorSchedule(doctorId, page, size);
        } else {
            finish();
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBarNoBack("医生详情");
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        doctorInfoLl.setVisibility(View.GONE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            doctorInfoLl.setVisibility(View.VISIBLE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

        DoctorScheduleAdapter adapter = new DoctorScheduleAdapter(this,null,null,"");
        recyclerView.setAdapter(adapter);

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
    public void getDoctorScheduleSucc(DoctorBean doctorBean) {
        doctorName.setText(doctorBean.getRealName());
        info.setText(doctorBean.getInfo());
        ImageLoader.getIns(this).load(ApiConstant.API_SERVER_URL + doctorBean.getPortraint(),
                doctorPortrait, R.mipmap.portraint_default, R.mipmap.portraint_default);

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
