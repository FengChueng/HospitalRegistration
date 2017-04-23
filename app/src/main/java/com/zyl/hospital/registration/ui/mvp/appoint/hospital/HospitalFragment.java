package com.zyl.hospital.registration.ui.mvp.appoint.hospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.adapter.HospitalAdapter;
import com.zyl.hospital.registration.base.MvpBaseFragment;
import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.mvp.appoint.department.DeptActivity;
import com.zyl.hospital.registration.utils.RouterUtils;
import com.zyl.hospital.registration.utils.ToastUtils;
import com.zyl.hospital.registration.widget.wave.WaveSideBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 预约医生
 */
public class HospitalFragment extends MvpBaseFragment<HospitalContract.HospitalPresenter> implements HospitalContract.HospitalView {
    private static final String TAG = "HospitalFragment";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_view)
    WaveSideBarView mSideBarView;

    private Integer type = 1;
    BaseQuickAdapter adapter;

    private int page = 0;
    private int size = 30;
    private ArrayList<Hospital> hospitalArrayList;

    public static HospitalFragment newInstance(Integer type) {
        HospitalFragment hospitalFragment = new HospitalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        hospitalFragment.setArguments(bundle);
        return hospitalFragment;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hospitalArrayList = new ArrayList<>();
        adapter = new HospitalAdapter(getActivity(), hospitalArrayList);
        mRecyclerView.setAdapter(adapter);

//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                page++;
//                mPresenter.getHospitalList(page, size);
//            }
//        });


        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Hospital hospital = (Hospital) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_HOSPITAL_ID, hospital.getHospitalId());
                RouterUtils.gotoNext(getActivity(), DeptActivity.class, bundle);
                super.onItemClick(adapter, view, position);
            }

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.wave_list;
    }

    @Override
    protected void onLazyLoad() {
        mPresenter.getHospitalList(page, size);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt("type");
        }
    }

    @Override
    public void getHospitalSucc(List<Hospital> hospitals) {
        hospitalArrayList.addAll(hospitals);
        adapter.addData(hospitalArrayList);
    }

    @Override
    public void getHospitalError(String msg) {
        ToastUtils.showMetrailToast(getActivity(), "加载失败");
    }

    @Override
    protected HospitalContract.HospitalPresenter createPresenter() {
        return new HospitalPresenterImpl(this);
    }
}
