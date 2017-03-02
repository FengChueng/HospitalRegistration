package com.zyl.hospital.registration.ui.mvp.user.login;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginRegisterPresenterImpl extends LoginRegisterContract.LoginPresenter{
    LoginRegisterContract.LoginRegisterView mLoginRegisterView;
    LoginRegisterRegisterModelImpl mLoginModel;
    public LoginRegisterPresenterImpl(LoginRegisterContract.LoginRegisterView mLoginRegisterView){
        this.mLoginRegisterView = mLoginRegisterView;
        mLoginModel = new LoginRegisterRegisterModelImpl();
    }
    @Override
    void login(String mobile, String password) {
        
    }

    @Override
    void register(String mobile, String password) {

    }
}
