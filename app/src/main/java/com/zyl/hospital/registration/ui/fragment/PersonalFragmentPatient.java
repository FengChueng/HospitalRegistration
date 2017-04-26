package com.zyl.hospital.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.BaseFragment;
import com.zyl.hospital.registration.utils.SPUtils;

import butterknife.OnClick;


/**
 * 个人中心
 */
public class PersonalFragmentPatient extends BaseFragment {
    private static final String TAG = "PersonalFragmentPatient";
    private Integer type = 1;
    private LinearLayout exitLogin;

    public static PersonalFragmentPatient newInstance(Integer type) {
        PersonalFragmentPatient appointmentFragmentDoctor = new PersonalFragmentPatient();
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
        return R.layout.fragment_personal_patient;
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

    @OnClick(R.id.exit_login)
    public void onClick() {
        SPUtils.clearAllSP(getActivity());
    }
}