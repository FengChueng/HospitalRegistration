package com.zyl.hospital.registration.ui.mvp.user.login;


import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginRegisterModelImpl implements LoginRegisterContract.LoginRegisterModel {
    @Override
    public Observable<ResponseEntity<?>> login(String mobile, String password, int role) {
        if (role == ApiConstant.PATIENT) {
            return Api.getInstance()
                    .createService(ApiService.class)
                    .patientlogin(mobile, password)
                    .compose(RxSchedulers.<ResponseEntity<?>>io_main());
        } else {
            return Api.getInstance()
                    .createService(ApiService.class)
                    .doctorlogin(mobile, password)
                    .compose(RxSchedulers.<ResponseEntity<?>>io_main());
        }
    }

    @Override
    public Observable<ResponseEntity<PatientBean>> register(String mobile, String password) {
        return Api
                .getInstance()
                .createService(ApiService.class)
                .patientregister(mobile, password)
                .compose(RxSchedulers.<ResponseEntity<PatientBean>>io_main());
    }
}
