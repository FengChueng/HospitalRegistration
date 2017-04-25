package com.zyl.hospital.registration.ui.mvp.appoint.hospital;

import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class HospitalModelImpl implements HospitalContract.HospitalModel{
    @Override
    public Observable<ResponseEntity<List<Hospital>>> getHospitalList(int page, int size) {
        return Api
                .getInstance()
                .createService(ApiService.class)
                .getHospitals()
                .compose(RxSchedulers.<ResponseEntity<List<Hospital>>>io_main());
    }
}
