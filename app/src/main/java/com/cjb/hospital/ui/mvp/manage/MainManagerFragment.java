package com.cjb.hospital.ui.mvp.manage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cjb.hospital.adapter.ManagerFragmentPagerAdapter;
import com.cjb.hospital.base.BaseFragment;
import com.cjb.hospital.constants.ApiConstant;
import com.zyl.hospital.hospital.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 病人预约管理
 */
public class MainManagerFragment extends BaseFragment {
    private static final String TAG = "AppointmentFragmentPatient";
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private int type;

    private int[] status = {ApiConstant.APPOINT_UN_HANDLE, ApiConstant.APPOINT_HANDLE_ING, ApiConstant.APPOINT_COMPLETED};

    private ManagerFragmentPagerAdapter fragmentPagerAdapter;
    private ArrayList<PatientManagerFragment> pagerList;

    public static MainManagerFragment newInstance(Integer type) {
        MainManagerFragment appointmentFragmentDoctor = new MainManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        appointmentFragmentDoctor.setArguments(bundle);
        return appointmentFragmentDoctor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt("type");
        }

        pagerList = new ArrayList<>();
        fragmentPagerAdapter = new ManagerFragmentPagerAdapter(getChildFragmentManager());
//        switch (type) {
//            case ApiConstant.DOCTOR:
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_UN_HANDLE));
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_HANDLE_ING));
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_COMPLETED));
//                break;
//            case ApiConstant.PATIENT:
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_UN_HANDLE));
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_HANDLE_ING));
//                pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_COMPLETED));
//                break;
//        }

        pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_UN_HANDLE));
        pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_HANDLE_ING));
        pagerList.add(PatientManagerFragment.newInstance(ApiConstant.APPOINT_COMPLETED));

        fragmentPagerAdapter.setPagerList(pagerList);
        viewpager.setAdapter(fragmentPagerAdapter);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(0);
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
}