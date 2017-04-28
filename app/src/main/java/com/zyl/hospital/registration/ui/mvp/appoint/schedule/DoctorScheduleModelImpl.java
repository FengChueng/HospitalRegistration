package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import com.zyl.hospital.registration.bean.Appointment;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class DoctorScheduleModelImpl implements DoctorScheduleContract.DoctorScheduleModel {
    @Override
    public Observable<ResponseEntity<DoctorBean>> getDoctorSchedule(String doctorId, int page, int size) {
        return Api.getInstance().createService(ApiService.class).getDoctor(doctorId,page,size);
    }

    @Override
    public Observable<ResponseEntity<Appointment>> makeAppointment(String hospitalId, String deptId, String patientId, String doctorId, String doctorScheduleId) {

        return Api.getInstance().createService(ApiService.class).makeAppointment(hospitalId,deptId,patientId,doctorId,doctorScheduleId);
    }
}
