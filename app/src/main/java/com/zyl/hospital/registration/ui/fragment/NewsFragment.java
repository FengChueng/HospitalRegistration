package com.zyl.hospital.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.BaseFragment;

/**
 * 新闻资讯
 */
public class NewsFragment extends BaseFragment {
    private static final String TAG = "NewsFragment";

    private Integer type = 1;

    public static NewsFragment newInstance(Integer type){
        NewsFragment allOrderFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        allOrderFragment.setArguments(bundle);
        return allOrderFragment;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
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
