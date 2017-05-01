package com.cjb.hospital.ui.mvp.manage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cjb.hospital.adapter.AppointmentAdapter;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.ui.mvp.appoint.detail.AppointDetailActivity;
import com.cjb.hospital.utils.RouterUtils;
import com.cjb.hospital.widget.WrapContentLinearLayoutManager;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.base.MvpBaseFragment;
import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.utils.LogUtils;
import com.cjb.hospital.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 病人预约管理
 */
public class PatientManagerFragment extends MvpBaseFragment<PatientManagerContract.ManagerPresenter> implements PatientManagerContract.ManagerView {
    private static final String TAG = "AppointmentFragmentPatient";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //预约状态
    private int status;
    //角色
    private int role;
    //账号
    private String account;
    //分页
    private int page = 0;
    //每次请求的条数
    private int size = 30;
    private List<Appointment> data;
    private AppointmentAdapter adapter;
    private boolean isFirstIn = true;

    public static PatientManagerFragment newInstance(Integer status) {
        PatientManagerFragment appointmentFragmentDoctor = new PatientManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        appointmentFragmentDoctor.setArguments(bundle);
        return appointmentFragmentDoctor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            status = args.getInt("status");
        }
        data = new ArrayList<>();
        adapter = new AppointmentAdapter(getActivity(),data);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//        recyclerView.addItemDecoration(new PinnedHeaderDecoration());
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Appointment appointment = (Appointment) baseQuickAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_APPOINTMENT_ID,appointment.getAppointId());
                RouterUtils.gotoNext(getActivity(), AppointDetailActivity.class,bundle);
            }
        });
        if(status== ApiConstant.APPOINT_UN_HANDLE&&isFirstIn){
            onLazyLoad();
            isFirstIn = false;
        }
    }

    @Override
    protected void initParam() {
        role = (int) SPUtils.getSP(getActivity(), AppConstants.KEY_ROLE,ApiConstant.PATIENT);
        account = (String) SPUtils.getSP(getActivity(), AppConstants.KEY_USER_ID,"");
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_pager;
    }

    @Override
    protected void onLazyLoad() {
        LogUtils.i(role + "," + account+","+status);
        if(role == ApiConstant.PATIENT){
            mPresenter.queryByPatientIdAndStatus(account,status,page,size);
        }else if(role == ApiConstant.DOCTOR){
            mPresenter.queryByDoctorIdAndStatus(account,status,page,size);
        }
    }

    @Override
    protected PatientManagerContract.ManagerPresenter createPresenter() {
        return new PatientManagerPresenterImpl(this);
    }

    @Override
    public void querySucc(List<Appointment> appointments) {
        if(appointments!=null){
            adapter.setNewData(appointments);
        }
    }

    @Override
    public void queryError(String msg) {
        //ToastUtils.showMetrailToast(getActivity(),msg);
    }
}