package com.cjb.hospital.ui.mvp.manage;

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.Appointment;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface PatientManagerContract {
    abstract class ManagerPresenter extends BasePresenter<BaseView> {
        abstract void queryByPatientIdAndStatus(String patientId,int status,int page,int size);
        abstract void queryByDoctorIdAndStatus(String patientId,int status,int page,int size);
    }

    interface ManagerView extends BaseView{
        void querySucc(List<Appointment> Appointment);
        void queryError(String msg);
    }

    interface ManagerModel extends BaseModel{
        Observable<ResponseEntity<List<Appointment>>> queryByPatientIdAndStatus(String patientId, int status, int page, int size);

        Observable<ResponseEntity<List<Appointment>>> queryByDoctorIdAndStatus(String patientId,int status,int page,int size);
    }

}
