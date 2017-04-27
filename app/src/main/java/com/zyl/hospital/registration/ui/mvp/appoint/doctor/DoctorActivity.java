package com.zyl.hospital.registration.ui.mvp.appoint.doctor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.adapter.DoctorAdapter;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.mvp.appoint.schedule.DoctorScheduleActivity;
import com.zyl.hospital.registration.utils.RouterUtils;
import com.zyl.hospital.registration.utils.SPUtils;
import com.zyl.hospital.registration.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoctorActivity extends MvpBaseActivity<DoctorContract.DoctorPresenter> implements DoctorContract.DoctorView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private DoctorAdapter doctorAdapter;
    private String deptId;

    private int page = 0;
    private int size = 30;
    private ArrayList<DoctorBean> deptArrayList;
    private String deptName;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            deptId = (String) params.get(AppConstants.KEY_DEPT_ID);
            deptName = (String) params.get(AppConstants.KEY_DEPT_NAME);
            SPUtils.putSP(this,AppConstants.KEY_DEPT_NAME,"");
            mPresenter.getDoctor(deptId,page,size);
        }else{
            onBackPressed();
        }
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar("医生列表");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        deptArrayList = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(getActivity(), deptArrayList,deptName);
        mRecyclerView.setAdapter(doctorAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                DoctorBean doctorBean = (DoctorBean) baseQuickAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_DOCTOR_ID, doctorBean.getDoctorAccount());
                RouterUtils.gotoNext(getActivity(), DoctorScheduleActivity.class, bundle);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_doctor;
    }

    @Override
    protected DoctorContract.DoctorPresenter createPresenter() {
        return new DoctorPresenterImpl(this);
    }

    @Override
    public void getDoctorListSucc(List<DoctorBean> doctorBeans) {
        doctorAdapter.addData(doctorBeans);
    }

    @Override
    public void getDoctorListError(String msg) {
        ToastUtils.showMetrailToast(this,msg);
        mPresenter.getDoctor(deptId,page,size);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AppConstants.KEY_DEPT_ID, deptId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        deptId = (String) savedInstanceState.get(AppConstants.KEY_DEPT_ID);
    }

}
