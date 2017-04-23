package com.zyl.hospital.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.BaseFragment;

/**
 * 病人预约管理
 */
public class AppointmentManagerFragmentPatient extends BaseFragment {
    private static final String TAG = "AppointmentFragmentPatient";
    private Integer type = 1;

    public static AppointmentManagerFragmentPatient newInstance(Integer type){
        AppointmentManagerFragmentPatient appointmentFragmentDoctor = new AppointmentManagerFragmentPatient();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        appointmentFragmentDoctor.setArguments(bundle);
        return appointmentFragmentDoctor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return null;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_patient;
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