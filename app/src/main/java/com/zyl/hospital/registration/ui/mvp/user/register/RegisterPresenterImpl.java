package com.zyl.hospital.registration.ui.mvp.user.register;

/**
 * Created by Administrator on 2017/2/26.
 */

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    RegisterContract.RegisterView mRegisterView;
    RegisterModelImpl mRegisterModel;
    public RegisterPresenterImpl(RegisterContract.RegisterView mRegisterView){
        this.mRegisterView = mRegisterView;
        mRegisterModel = new RegisterModelImpl();
    }
    @Override
    void register(String mobile, String password) {
        
    }
}
