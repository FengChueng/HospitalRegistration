package com.cjb.hospital.ui.mvp.appoint.schedule;

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.ResponseEntity;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public interface DoctorScheduleContract {
    abstract class DoctorSchedulePresenter extends BasePresenter<BaseView>{
        abstract void getDoctorSchedule(String doctorId,int page,int size);

        abstract void makeAppointment(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId);

        abstract void getAppointmentInfo(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId);
    }

    interface DoctorScheduleView extends BaseView{
        void getDoctorScheduleSucc(DoctorBean doctorSchedule);
        void getDoctorScheduleError(String msg);
        void noData(String msg);


        void makeAppointmentSucc(Appointment appointment);
        void makeAppointmentError(String msg);


        void getAppointmentInfoSucc(AppointmentDetail appointmentDetail);
        void getAppointmentInfoError(String msg);

    }

    interface DoctorScheduleModel {
        Observable<ResponseEntity<DoctorBean>> getDoctorSchedule(String doctorId, int page, int size);
        Observable<ResponseEntity<Appointment>> makeAppointment(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId);
        Observable<ResponseEntity<AppointmentDetail>> getAppointmentInfo(String hospitalId, String deptId, String patientId, String doctorId, String doctorScheduleId);
    }

}
