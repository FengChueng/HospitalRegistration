package com.cjb.hospital.ui.mvp.appoint.hospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.RouterUtils;
import com.cjb.hospital.widget.wave.WaveSideBarView;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.adapter.HospitalAdapter;
import com.cjb.hospital.base.MvpBaseFragment;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.ui.mvp.appoint.department.DeptActivity;
import com.cjb.hospital.utils.LogUtils;
import com.cjb.hospital.utils.SPUtils;
import com.cjb.hospital.utils.ToastUtils;

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
    private boolean refresh = true;
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hospitalArrayList = new ArrayList<>();
        adapter = new HospitalAdapter(getActivity(), hospitalArrayList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //super.onItemClick(adapter, view, position);
                Hospital hospital = (Hospital) adapter.getItem(position);
                LogUtils.e("position:"+position+"com.cjb.hospital:"+hospital.toString());
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.KEY_HOSPITAL_ID, hospital.getHospitalId());
                RouterUtils.gotoNext(getActivity(), DeptActivity.class, bundle);
                SPUtils.putSP(getActivity(),AppConstants.KEY_HOSPITAL_ID,hospital.getHospitalId());
            }
        });
        onLazyLoad();
    }

    @Override
    public void getHospitalSucc(List<Hospital> hospitals) {
        if(hospitals == null){
            return;
        }

        hospitalArrayList.addAll(hospitals);
        if(refresh){
            adapter.setNewData(hospitals);
        }else{
            adapter.addData(hospitalArrayList);
        }
    }

    @Override
    public void getHospitalError(String msg) {
        ToastUtils.showMetrailToast(getActivity(), "加载失败");
        adapter.loadMoreFail();
    }

    @Override
    protected HospitalContract.HospitalPresenter createPresenter() {
        return new HospitalPresenterImpl(this);
    }
}
