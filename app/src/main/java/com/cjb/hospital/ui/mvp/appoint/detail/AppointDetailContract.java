package com.cjb.hospital.ui.mvp.appoint.detail;

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.bean.ResponseEntity;

import rx.Observable;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public interface AppointDetailContract {
    abstract class AppointDetailPresenter extends BasePresenter<BaseView> {
        abstract void getAppointmentDetail(String appointId);
        abstract void cancelAppointment(String appointId);
        abstract void startAppointment(String appointId);
        abstract void timeOutappointment(String appointId);
        abstract void completeAppointment(String appointId,String doctorAdvice);
    }

    interface AppointDetailView extends BaseView {
        void getAppointDetailSucc(AppointmentDetail appointmentDetail);
        void success(String msg);
        void error(String msg);
    }

    interface AppointDetailModel {
        Observable<ResponseEntity<AppointmentDetail>> getAppointmentDetail(String appointId);
        Observable<ResponseEntity<Appointment>> cancelAppointment(String appointId);
        Observable<ResponseEntity<Appointment>> startAppointment(String appointId);
        Observable<ResponseEntity<Appointment>> timeOutappointment(String appointId);
        Observable<ResponseEntity<Appointment>> completeAppointment(String appointId,String doctorAdvice);
    }

}
