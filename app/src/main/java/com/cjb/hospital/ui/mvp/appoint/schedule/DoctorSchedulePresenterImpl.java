package com.cjb.hospital.ui.mvp.appoint.schedule;

import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.rx.RxSubscribers;
import com.cjb.hospital.rx.RxSubscriber;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class DoctorSchedulePresenterImpl extends DoctorScheduleContract.DoctorSchedulePresenter {
    DoctorScheduleContract.DoctorScheduleView view;
    DoctorScheduleContract.DoctorScheduleModel modle;
    public DoctorSchedulePresenterImpl(DoctorScheduleContract.DoctorScheduleView view){
        this.view = view;
        this.modle = new DoctorScheduleModelImpl();
    }

    @Override
    void getDoctorSchedule(String doctorId, int page, int size) {
        addSubscription(modle.getDoctorSchedule(doctorId,page,size).subscribe(new RxSubscribers<DoctorBean>(view.getActivity()) {
            @Override
            protected void _onNext(DoctorBean doctorBean) {
                view.getDoctorScheduleSucc(doctorBean);
            }

            @Override
            protected void _onError(String message) {
                view.getDoctorScheduleError(message);
            }

            @Override
            protected void _noData(String msg) {
                view.noData(msg);
            }
        }));
    }

    @Override
    void makeAppointment(String hospitalId,String deptId,String patientId, String doctorId, String doctorScheduleId) {
        addSubscription(modle
                .makeAppointment(hospitalId, deptId,patientId,doctorId,doctorScheduleId)
                .subscribe(new RxSubscriber<ResponseEntity<Appointment>>(view.getActivity()) {
                    @Override
                    protected void _onNext(ResponseEntity<Appointment> appointmentResponseEntity) {
                        if (appointmentResponseEntity == null) {
                            view.makeAppointmentError("预约失败");
                        }
                        int status = appointmentResponseEntity.getStatus();
                        if(status == ApiConstant.SUCCESS){
                            view.makeAppointmentSucc(appointmentResponseEntity.getData());
                        }else{
                            view.makeAppointmentError("预约失败");
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        view.makeAppointmentError(message);
                    }
                }));
    }

    @Override
    void getAppointmentInfo(String hospitalId, String deptId, String patientId, String doctorId, String doctorScheduleId) {
        addSubscription(modle.getAppointmentInfo(hospitalId,deptId,patientId,doctorId,doctorScheduleId).subscribe(new RxSubscribers<AppointmentDetail>(view.getActivity()) {
            @Override
            protected void _onNext(AppointmentDetail appointmentDetail) {
                view.getAppointmentInfoSucc(appointmentDetail);
            }

            @Override
            protected void _onError(String message) {
                view.getAppointmentInfoError(message);
            }

            @Override
            protected void _noData(String msg) {
                view.getAppointmentInfoError(msg);
            }
        }));
    }
}
