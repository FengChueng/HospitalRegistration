package com.zyl.hospital.registration.ui.mvp.appoint.department;

import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class DeptModelImpl implements DeptContract.DeptModel {

    @Override
    public Observable<ResponseEntity<List<Department>>> getDeptList(String hospitalId, int page, int size) {
        return Api.getInstance()
                .createService(ApiService.class)
                .getDepartsments(hospitalId,page,size)
                .compose(RxSchedulers.<ResponseEntity<List<Department>>>io_main());
    }
}
