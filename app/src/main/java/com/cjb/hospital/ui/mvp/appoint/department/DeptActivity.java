package com.cjb.hospital.ui.mvp.appoint.department;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cjb.hospital.adapter.DeptAdapter;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.Department;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.ui.mvp.appoint.doctor.DoctorActivity;
import com.cjb.hospital.utils.RouterUtils;
import com.cjb.hospital.utils.SPUtils;
import com.cjb.hospital.utils.ToastUtils;
import com.zyl.hospital.hospital.R;

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Department department = (Department) baseQuickAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_DEPT_ID, department.getDeptId());
                bundle.putString(AppConstants.KEY_DEPT_NAME,department.getDeptName());
                RouterUtils.gotoNext(getActivity(), DoctorActivity.class, bundle);
                SPUtils.putSP(getActivity(),AppConstants.KEY_DEPT_ID,department.getDeptId());
                SPUtils.putSP(getActivity(),AppConstants.KEY_DEPT_NAME,department.getDeptName());
            }
        });
//        //空布局
//        View emptyView = this.getLayoutInflater().inflate(R.layout.layout_status_empty_view,null);
//        adapter.setEmptyView(emptyView);

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
        if (departments != null) {
            adapter.addData(departments);
        }
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
