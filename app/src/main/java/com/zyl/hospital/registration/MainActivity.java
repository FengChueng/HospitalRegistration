package com.zyl.hospital.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.annotation.aspect.CheckLogin;
import com.annotation.aspect.SingleClick;
import com.zyl.hospital.registration.base.BaseActivity;
import com.zyl.hospital.registration.ui.mvp.user.userinfo.UserInfoActivity;
import com.zyl.hospital.registration.utils.LogUtils;
import com.zyl.hospital.registration.utils.SPUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void initParams(Bundle params) {
        SPUtils.putSP(this,"isLogin",false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @SingleClick
    public void checkLogin(View view) {
        LogUtils.d("checkLogin");
        check();
    }

    @CheckLogin
    private void check() {
        LogUtils.d("check");
    }

    @SingleClick
    public void user_info(View view) {
        this.startActivity(new Intent(this, UserInfoActivity.class));
    }
}
