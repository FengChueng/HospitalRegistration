package com.cjb.hospital.ui.mvp.appoint.doctor;

import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;
import com.cjb.hospital.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class DoctorModelImpl implements DoctorContract.DoctorModel {

    @Override
    public Observable<ResponseEntity<List<DoctorBean>>> getDeptList(String deptId, int page, int size) {
        return Api.getInstance()
                .createService(ApiService.class)
                .getDoctors(deptId,page,size)
                .compose(RxSchedulers.<ResponseEntity<List<DoctorBean>>>io_main());
    }
}
