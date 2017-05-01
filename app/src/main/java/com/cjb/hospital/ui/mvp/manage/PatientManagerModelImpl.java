package com.cjb.hospital.ui.mvp.manage;

import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;
import com.cjb.hospital.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public class PatientManagerModelImpl implements PatientManagerContract.ManagerModel {
    @Override
    public Observable<ResponseEntity<List<Appointment>>> queryByPatientIdAndStatus(String patientId, int status, int page, int size) {
        return Api
                .getInstance()
                .createService(ApiService.class)
                .queryByPatientIdAndStatus(patientId,status,page,size)
                .compose(RxSchedulers.<ResponseEntity<List<Appointment>>>io_main());
    }

    @Override
    public Observable<ResponseEntity<List<Appointment>>> queryByDoctorIdAndStatus(String patientId, int status, int page, int size) {
        return Api
                .getInstance()
                .createService(ApiService.class)
                .queryByDoctorIdAndStatus(patientId,status,page,size)
                .compose(RxSchedulers.<ResponseEntity<List<Appointment>>>io_main());
    }
}
