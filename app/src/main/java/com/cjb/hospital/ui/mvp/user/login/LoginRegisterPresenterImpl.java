package com.cjb.hospital.ui.mvp.user.login;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.bean.BaseBean;
import com.cjb.hospital.bean.PatientBean;
import com.cjb.hospital.rx.RxSubscriber;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginRegisterPresenterImpl extends LoginRegisterContract.LoginPresenter {
    LoginRegisterContract.LoginRegisterView mLoginRegisterView;
    LoginRegisterModelImpl mLoginModel;

    public LoginRegisterPresenterImpl(LoginRegisterContract.LoginRegisterView mLoginRegisterView) {
        this.mLoginRegisterView = mLoginRegisterView;
        mLoginModel = new LoginRegisterModelImpl();
    }

    @Override
    void login(String mobile, String password, int role) {
        addSubscription(mLoginModel
                .login(mobile, password, role)
                .subscribe(new RxSubscriber<ResponseEntity<?>>(mLoginRegisterView.getActivity()) {
                    @Override
                    protected void _onNext(ResponseEntity<?> patientBeanResponseEntity) {
                        int status = patientBeanResponseEntity.getStatus();
                        if (status == ApiConstant.SUCCESS) {
                            mLoginRegisterView.loginSuccess((BaseBean) patientBeanResponseEntity.getData());
                        } else {
                            mLoginRegisterView.loginError(patientBeanResponseEntity.getMsg());
                        }
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
                .register(mobile, password)
                .subscribe(new RxSubscriber<ResponseEntity<PatientBean>>(mLoginRegisterView.getActivity()) {
                    @Override
                    protected void _onNext(ResponseEntity<PatientBean> patientBeanResponseEntity) {
                        int status = patientBeanResponseEntity.getStatus();
                        if (status == ApiConstant.SUCCESS) {
                            mLoginRegisterView.registerSuccess(patientBeanResponseEntity.getData());
                        } else {
                            mLoginRegisterView.registerError(patientBeanResponseEntity.getMsg());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mLoginRegisterView.registerError(message);
                    }
                }));
    }
}
