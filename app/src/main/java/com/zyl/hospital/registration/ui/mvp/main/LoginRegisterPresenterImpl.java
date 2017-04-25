package com.zyl.hospital.registration.ui.mvp.main;

import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.rx.RxSubscriber;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginRegisterPresenterImpl extends MainContract.MainPresenter {
    MainContract.MainView mLoginRegisterView;
    LoginRegisterModelImpl mLoginModel;
    public LoginRegisterPresenterImpl(MainContract.MainView mLoginRegisterView){
        this.mLoginRegisterView = mLoginRegisterView;
        mLoginModel = new LoginRegisterModelImpl();
    }
    @Override
    void login(String mobile, String password,int role) {
        addSubscription(mLoginModel
                .login(mobile,password,role)
                .subscribe(new RxSubscriber<ResponseEntity<?>>(mLoginRegisterView.getActivity()) {
                    @Override
                    protected void _onNext(ResponseEntity<?> patientBeanResponseEntity) {

                        mLoginRegisterView.loginSuccess(patientBeanResponseEntity);
                    }

                    @Override
                    protected void _onError(String message) {
                        mLoginRegisterView.loginError(message);
                    }
                }));
    }

    @Override
    void register(String mobile, String password) {
        addSubscription(mLoginModel
                .register(mobile,password)
                .subscribe(new RxSubscriber<ResponseEntity<PatientBean>>(mLoginRegisterView.getActivity()) {
            @Override
            protected void _onNext(ResponseEntity<PatientBean> patientBeanResponseEntity) {
                mLoginRegisterView.registerSuccess(patientBeanResponseEntity);
            }

            @Override
            protected void _onError(String message) {
                mLoginRegisterView.registerError(message);
            }
        }));
    }
}
