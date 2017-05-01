package com.cjb.hospital.ui.mvp.appoint.hospital;

import com.cjb.hospital.rx.RxSchedulers;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;

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
