package com.cjb.hospital.ui.mvp.appoint.department;

import com.cjb.hospital.rx.RxSchedulers;
import com.cjb.hospital.bean.Department;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;

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
