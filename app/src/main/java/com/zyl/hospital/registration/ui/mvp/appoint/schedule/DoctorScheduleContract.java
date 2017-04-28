package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.Appointment;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.ResponseEntity;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public interface DoctorScheduleContract {
    abstract class DoctorSchedulePresenter extends BasePresenter<BaseView>{
        abstract void getDoctorSchedule(String doctorId,int page,int size);
        abstract void makeAppointment(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId);
    }

    interface DoctorScheduleView extends BaseView{
        void getDoctorScheduleSucc(DoctorBean doctorSchedule);
        void getDoctorScheduleError(String msg);
        void noData(String msg);


        void makeAppointmentSucc(String msg);
        void makeAppointmentError(String msg);

    }

    interface DoctorScheduleModel {
        Observable<ResponseEntity<DoctorBean>> getDoctorSchedule(String doctorId, int page, int size);
        Observable<ResponseEntity<Appointment>> makeAppointment(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId);
    }

}
