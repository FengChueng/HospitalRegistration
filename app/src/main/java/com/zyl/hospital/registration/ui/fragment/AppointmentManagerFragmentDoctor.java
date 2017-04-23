package com.zyl.hospital.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.BaseFragment;

/**
 * 医生预约管理
 */
public class AppointmentManagerFragmentDoctor extends BaseFragment {
    private static final String TAG = "AppointmentFragmentDoctor";

    private Integer type = 1;   //1 清洁，2 维修

    public static AppointmentManagerFragmentDoctor newInstance(Integer type){
        AppointmentManagerFragmentDoctor appointmentFragmentDoctor = new AppointmentManagerFragmentDoctor();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        appointmentFragmentDoctor.setArguments(bundle);
        return appointmentFragmentDoctor;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_doctor;
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt("type");
        }
    }
}