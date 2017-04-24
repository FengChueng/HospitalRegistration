package com.zyl.hospital.registration.ui.mvp.appoint.doctor;

import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class DoctorModelImpl implements DoctorContract.DoctorModel {

    @Override
    public Observable<ResultEntity<List<DoctorBean>>> getDeptList(String deptId, int page, int size) {
        return Api.getInstance()
                .createService(ApiService.class)
                .getDoctors(deptId,page,size)
                .compose(RxSchedulers.<ResultEntity<List<DoctorBean>>>io_main());
    }
}
