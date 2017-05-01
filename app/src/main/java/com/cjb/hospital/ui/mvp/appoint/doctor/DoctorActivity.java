package com.cjb.hospital.ui.mvp.appoint.doctor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cjb.hospital.adapter.DoctorAdapter;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.RouterUtils;
import com.cjb.hospital.utils.ToastUtils;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.ui.mvp.appoint.schedule.DoctorScheduleActivity;
import com.cjb.hospital.utils.LogUtils;
import com.cjb.hospital.utils.SPUtils;

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
            SPUtils.putSP(this,AppConstants.KEY_DEPT_ID,deptId);
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
//        mRecyclerView.addItemDecoration(new PinnedHeaderDecoration());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                DoctorBean doctorBean = (DoctorBean) baseQuickAdapter.getItem(position);
                Bundle bundle = new Bundle();
                LogUtils.i("doctorId:"+doctorBean.getDoctorAccount());
                bundle.putSerializable(AppConstants.KEY_DOCTOR_OBJ,doctorBean);
//                bundle.putString(AppConstants.KEY_DOCTOR_ID, doctorBean.getDoctorAccount());
                RouterUtils.gotoNext(getActivity(), DoctorScheduleActivity.class, bundle);
            }
        });
//        //空布局
//        View emptyView = this.getLayoutInflater().inflate(R.layout.layout_status_empty_view,null);
//        doctorAdapter.setEmptyView(emptyView);
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
        if(doctorBeans!=null){
            doctorAdapter.addData(doctorBeans);
        }
    }

    @Override
    public void getDoctorListError(String msg) {
        ToastUtils.showMetrailToast(this,msg);
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
