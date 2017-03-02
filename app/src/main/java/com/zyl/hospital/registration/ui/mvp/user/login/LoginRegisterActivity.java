package com.zyl.hospital.registration.ui.mvp.user.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;

import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterActivity extends MvpBaseActivity<LoginRegisterContract.LoginPresenter> implements LoginRegisterContract.LoginRegisterView {
    @BindView(R.id.login_register)
    MaterialLoginView loginRegister;

    //extends MvpBaseActivity
    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        ((DefaultLoginView) loginRegister.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout username, TextInputLayout password) {
                String name = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                if (name.isEmpty()) {
                    username.setErrorEnabled(true);
                    username.setError("用户名不可以为空");
                } else if (pass.isEmpty()) {
                    password.setErrorEnabled(true);
                    password.setError("密码不可以为空");
                } else {
                    mPresenter.login(name, pass);
                }
            }
        });

        ((DefaultRegisterView) loginRegister.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout username, TextInputLayout password, TextInputLayout repeatPassword) {
                String name = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String repeatPass = repeatPassword.getEditText().getText().toString();
                if (name.isEmpty()) {
                    username.setErrorEnabled(true);
                    username.setError("用户名不可以为空");
                } else if (pass.isEmpty()) {
                    password.setErrorEnabled(true);
                    password.setError("密码不可以为空");
                } else if (repeatPass.isEmpty()) {
                    repeatPassword.setErrorEnabled(true);
                    repeatPassword.setError("密码不可以为空");
                } else if (!pass.equals(repeatPass)) {
                    repeatPassword.setErrorEnabled(true);
                    repeatPassword.setError("两次密码输入不一致");
                } else {
                    mPresenter.register(name, pass);
                }
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_login_register;
    }

    @Override
    protected LoginRegisterContract.LoginPresenter createPresenter() {
        return new LoginRegisterPresenterImpl(this);
    }


    //implements LoginRegisterContract.LoginRegisterView
    @Override
    public void loginSuccess(ResultEntity<UserEntity> userEntity) {

    }

    @Override
    public void loginError() {

    }

    @Override
    public void registerSuccess(ResultEntity<UserEntity> userEntity) {

    }

    @Override
    public void registerError() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
