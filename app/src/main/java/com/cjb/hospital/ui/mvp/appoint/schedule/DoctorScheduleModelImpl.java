package com.cjb.hospital.ui.mvp.appoint.schedule;

import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.rx.RxSchedulers;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.http.api.ApiService;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class DoctorScheduleModelImpl implements DoctorScheduleContract.DoctorScheduleModel {
    @Override
    public Observable<ResponseEntity<DoctorBean>> getDoctorSchedule(String doctorId, int page, int size) {
        return Api.getInstance()
                .createService(ApiService.class)
                .getDoctor(doctorId,page,size)
                .compose(RxSchedulers.<ResponseEntity<DoctorBean>>io_main());
    }

    @Override
    public Observable<ResponseEntity<Appointment>> makeAppointment(String hospitalId, String deptId, String patientId, String doctorId, String doctorScheduleId) {

        return Api.getInstance()
                .createService(ApiService.class)
                .makeAppointment(hospitalId,deptId,patientId,doctorId,doctorScheduleId)
                .compose(RxSchedulers.<ResponseEntity<Appointment>>io_main());
    }

    @Override
    public Observable<ResponseEntity<AppointmentDetail>> getAppointmentInfo(String hospitalId, String deptId, String patientId, String doctorId, String doctorScheduleId) {
        return Api.getInstance()
                .createService(ApiService.class)
                .appointmentInfo(hospitalId,deptId,patientId,doctorId,doctorScheduleId)
                .compose(RxSchedulers.<ResponseEntity<AppointmentDetail>>io_main());
    }
}
