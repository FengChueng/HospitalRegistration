package com.zyl.hospital.registration.ui.mvp.user.registrationinfo;

/**
 * Created by Administrator on 2017/2/26.
 */

public class RegistrationPresenterImpl extends RegistrationContract.RegistrationPresenter {
    RegistrationContract.RegistrationView mRegistrationView;
    RegistrationModelImpl mLoginModel;
    public RegistrationPresenterImpl(RegistrationContract.RegistrationView mRegistrationView){
        this.mRegistrationView = mRegistrationView;
        mLoginModel = new RegistrationModelImpl();
    }
    @Override
    void getRegistration(String mobile, String password) {
        
    }
}
