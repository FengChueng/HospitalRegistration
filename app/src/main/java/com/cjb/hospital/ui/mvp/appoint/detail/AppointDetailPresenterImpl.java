package com.cjb.hospital.ui.mvp.appoint.detail;

import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.rx.RxSubscribers;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class AppointDetailPresenterImpl extends AppointDetailContract.AppointDetailPresenter {
    AppointDetailContract.AppointDetailView view;
    AppointDetailContract.AppointDetailModel modle;
    public AppointDetailPresenterImpl(AppointDetailContract.AppointDetailView view){
        this.view = view;
        this.modle = new AppointDetailModelImpl();
    }

    @Override
    void getAppointmentDetail(String appointId) {
        addSubscription(modle.getAppointmentDetail(appointId)
                .subscribe(new RxSubscribers<AppointmentDetail>(view.getActivity()) {

                    @Override
                    protected void _onNext(AppointmentDetail appointmentDetail) {
                        view.getAppointDetailSucc(appointmentDetail);
                    }

                    @Override
                    protected void _onError(String message) {
                        view.error(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.error(msg);
                    }

                }));
    }

    @Override
    void cancelAppointment(String appointId) {
        addSubscription(modle.cancelAppointment(appointId)
                .subscribe(new RxSubscribers<Appointment>(view.getActivity()) {

                    @Override
                    protected void _onNext(Appointment appointment) {
                        view.success("取消成功");
                    }

                    @Override
                    protected void _onError(String message) {
                        view.success(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.success(msg);
                    }
                }));
    }

    @Override
    void startAppointment(String appointId) {
        addSubscription(modle.startAppointment(appointId)
                .subscribe(new RxSubscribers<Appointment>(view.getActivity()) {

                    @Override
                    protected void _onNext(Appointment appointment) {
                        view.success("诊断开始");
                    }

                    @Override
                    protected void _onError(String message) {
                        view.success(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.success(msg);
                    }
                }));
    }

    @Override
    void timeOutappointment(String appointId) {
        addSubscription(modle.timeOutappointment(appointId)
                .subscribe(new RxSubscribers<Appointment>(view.getActivity()) {

                    @Override
                    protected void _onNext(Appointment appointment) {
                        view.success("超时未就诊");
                    }

                    @Override
                    protected void _onError(String message) {
                        view.success(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.success(msg);
                    }
                }));
    }

    @Override
    void completeAppointment(String appointId, String doctorAdvice) {
        addSubscription(modle.completeAppointment(appointId,doctorAdvice)
                .subscribe(new RxSubscribers<Appointment>(view.getActivity()) {

                    @Override
                    protected void _onNext(Appointment appointment) {
                        view.success("诊断结束");
                    }

                    @Override
                    protected void _onError(String message) {
                        view.success(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.success(msg);
                    }
                }));
    }
}
