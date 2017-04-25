package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.DoctorSchedule;
import com.zyl.hospital.registration.constants.AppConstants;

public class DoctorScheduleActivity extends MvpBaseActivity<DoctorScheduleContract.DoctorSchedulePresenter> implements DoctorScheduleContract.DoctorScheduleView{

    private String doctorId;
    private int page = 0;
    private int size = 30;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            doctorId = (String) params.get(AppConstants.KEY_DOCTOR_ID);
            //mPresenter.getDoctorSchedule(doctorId,page,size);
        }else{
            finish();
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

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
    public void getDoctorScheduleSucc(DoctorSchedule doctorSchedule) {

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
