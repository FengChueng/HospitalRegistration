package com.cjb.hospital.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cjb.hospital.base.BaseFragment;
import com.zyl.hospital.hospital.R;


/**
 * 个人中心
 */
public class PersonalFragmentDoctor extends BaseFragment {
    private static final String TAG = "PersonalFragmentDoctor";
    private Integer type = 1;

    public static PersonalFragmentDoctor newInstance(Integer type){
        PersonalFragmentDoctor personalFragmentDoctor = new PersonalFragmentDoctor();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        personalFragmentDoctor.setArguments(bundle);
        return personalFragmentDoctor;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_info;
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
