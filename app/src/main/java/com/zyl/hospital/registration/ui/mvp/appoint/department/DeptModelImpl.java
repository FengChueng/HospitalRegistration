package com.zyl.hospital.registration.ui.mvp.appoint.department;

import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class DeptModelImpl implements DeptContract.DeptModel {

    @Override
    public Observable<ResultEntity<List<Department>>> getDeptList(String hospitalId, int page, int size) {
        return Api.getInstance().createService(ApiService.class)
                .getDepartsments(hospitalId,page,size);
    }
}
