package com.zyl.hospital.registration.ui.mvp.user.login;

import com.zyl.hospital.registration.bean.BaseBean;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.rx.RxSubscriber;

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
                .subscribe(new RxSubscriber<ResultEntity<?>>(mLoginRegisterView.getActivity()) {
                    @Override
                    protected void _onNext(ResultEntity<?> patientBeanResultEntity) {
                        int status = patientBeanResultEntity.getStatus();
                        if (status == ApiConstant.SUCCESS) {
                            mLoginRegisterView.loginSuccess((BaseBean) patientBeanResultEntity.getData());
                        } else {
                            mLoginRegisterView.loginError(patientBeanResultEntity.getMsg());
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
                .subscribe(new RxSubscriber<ResultEntity<PatientBean>>(mLoginRegisterView.getActivity()) {
                    @Override
                    protected void _onNext(ResultEntity<PatientBean> patientBeanResultEntity) {
                        int status = patientBeanResultEntity.getStatus();
                        if (status == ApiConstant.SUCCESS) {
                            mLoginRegisterView.registerSuccess(patientBeanResultEntity.getData());
                        } else {
                            mLoginRegisterView.registerError(patientBeanResultEntity.getMsg());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mLoginRegisterView.registerError(message);
                    }
                }));
    }
}
