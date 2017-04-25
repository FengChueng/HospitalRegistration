package com.zyl.hospital.registration.ui.mvp.user.userinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.annotation.aspect.SingleClick;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.ResponseEntity;

public class UserInfoActivity extends MvpBaseActivity<UserInfoContract.UserPresenter> implements UserInfoContract.UserView{
    //extends MvpBaseActivity
    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_user_info;
    }

    @Override
    protected UserInfoContract.UserPresenter createPresenter() {
        return new UserPresenterImpl(this);
    }

    //implements UserInfoContract.UserView
    @Override
    public void loadUserInfo(ResponseEntity<?> userEntity) {

    }

    @Override
    public void loadError() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @SingleClick
    public void back(View view) {
        finish();
    }
}
