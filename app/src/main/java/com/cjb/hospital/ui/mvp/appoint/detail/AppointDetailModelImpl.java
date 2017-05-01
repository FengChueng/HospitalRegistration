package com.cjb.hospital.ui.mvp.appoint.detail;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.rx.RxSchedulers;
import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class AppointDetailModelImpl implements AppointDetailContract.AppointDetailModel {

    @Override
    public Observable<ResponseEntity<AppointmentDetail>> getAppointmentDetail(String appointId) {
        return Api.getInstance().createService(ApiService.class)
                .queryDetailInfo(appointId).compose(RxSchedulers.<ResponseEntity<AppointmentDetail>>io_main());
    }

    @Override
    public Observable<ResponseEntity<Appointment>> cancelAppointment(String appointId) {
        return Api.getInstance().createService(ApiService.class)
                .cancelAppointment(appointId).compose(RxSchedulers.<ResponseEntity<Appointment>>io_main());
    }

    @Override
    public Observable<ResponseEntity<Appointment>> startAppointment(String appointId) {
        return Api.getInstance().createService(ApiService.class)
                .startAppointment(appointId).compose(RxSchedulers.<ResponseEntity<Appointment>>io_main());
    }

    @Override
    public Observable<ResponseEntity<Appointment>> timeOutappointment(String appointId) {
        return Api.getInstance().createService(ApiService.class)
                .timeOutappointment(appointId).compose(RxSchedulers.<ResponseEntity<Appointment>>io_main());
    }

    @Override
    public Observable<ResponseEntity<Appointment>> completeAppointment(String appointId,String doctorAdvice){
        return Api.getInstance().createService(ApiService.class)
                .completeAppointment(appointId,doctorAdvice).compose(RxSchedulers.<ResponseEntity<Appointment>>io_main());
    }

}
