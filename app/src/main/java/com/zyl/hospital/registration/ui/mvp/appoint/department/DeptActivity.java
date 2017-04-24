package com.zyl.hospital.registration.ui.mvp.appoint.department;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.adapter.DeptAdapter;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.mvp.appoint.doctor.DoctorActivity;
import com.zyl.hospital.registration.utils.RouterUtils;
import com.zyl.hospital.registration.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DeptActivity extends MvpBaseActivity<DeptContract.DeptPresenter> implements DeptContract.DeptView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private String hospitalId;
    private ArrayList<Department> deptArrayList;
    private DeptAdapter adapter;

    private int page = 0;
    private int size = 30;

    @Override
    protected void initParams(Bundle params) {
        if (params != null && !TextUtils.isEmpty(params.getString(AppConstants.KEY_HOSPITAL_ID))) {
            hospitalId = params.getString(AppConstants.KEY_HOSPITAL_ID);
            mPresenter.getDeptList(hospitalId,page,size);
        } else {
            //
            onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AppConstants.KEY_HOSPITAL_ID, hospitalId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hospitalId = (String) savedInstanceState.get(AppConstants.KEY_HOSPITAL_ID);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar("科室");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        deptArrayList = new ArrayList<>();
        adapter = new DeptAdapter(getActivity(), deptArrayList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Department department = (Department) baseQuickAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_DEPT_ID, department.getDeptId());
                bundle.putString(AppConstants.KEY_DEPT_NAME,department.getDeptName());
                RouterUtils.gotoNext(getActivity(), DoctorActivity.class, bundle);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dept;
    }

    @Override
    protected DeptContract.DeptPresenter createPresenter() {
        return new DeptPresenterImpl(this);
    }

    @Override
    public void getDeptListSucc(List<Department> departments) {
        adapter.addData(departments);
    }

    @Override
    public void getDeptListError(String msg) {
        ToastUtils.showMetrailToast(this,msg);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
