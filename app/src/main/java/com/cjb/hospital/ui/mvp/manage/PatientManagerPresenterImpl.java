package com.cjb.hospital.ui.mvp.manage;

import com.cjb.hospital.rx.RxSubscribers;
import com.cjb.hospital.bean.Appointment;

import java.util.List;

/**
 * Created by cjb
 */
public class PatientManagerPresenterImpl extends PatientManagerContract.ManagerPresenter {
    PatientManagerContract.ManagerView view;
    PatientManagerContract.ManagerModel model;

    public PatientManagerPresenterImpl(PatientManagerContract.ManagerView view){
        this.view = view;
        model = new PatientManagerModelImpl();
    }

    @Override
    void queryByPatientIdAndStatus(String patientId,int status,int page,int size){
        addSubscription(model
                .queryByPatientIdAndStatus(patientId,status,page,size)
                .subscribe(new RxSubscribers<List<Appointment>>(view.getActivity()) {

                    @Override
                    protected void _onNext(List<Appointment> appointments) {
                        view.querySucc(appointments);
                    }

                    @Override
                    protected void _onError(String message) {
                        view.queryError(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.queryError(msg);
                    }
                }));
    }

    @Override
    void queryByDoctorIdAndStatus(String patientId, int status, int page, int size) {
        addSubscription(model
                .queryByDoctorIdAndStatus(patientId,status,page,size)
                .subscribe(new RxSubscribers<List<Appointment>>(view.getActivity()) {

                    @Override
                    protected void _onNext(List<Appointment> appointments) {
                        view.querySucc(appointments);
                    }

                    @Override
                    protected void _onError(String message) {
                        view.queryError(message);
                    }

                    @Override
                    protected void _noData(String msg) {
                        view.queryError(msg);
                    }
                }));
    }
}
