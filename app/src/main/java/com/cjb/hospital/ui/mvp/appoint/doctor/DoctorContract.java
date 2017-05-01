package com.cjb.hospital.ui.mvp.appoint.doctor;

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.DoctorBean;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface DoctorContract {
    abstract class DoctorPresenter extends BasePresenter<BaseView> {
        abstract void getDoctor(String deptId, int page, int size);
    }

    interface DoctorView extends BaseView{
        void getDoctorListSucc(List<DoctorBean> doctorBeans);
        void getDoctorListError(String msg);
    }

    interface DoctorModel extends BaseModel{
        Observable<ResponseEntity<List<DoctorBean>>> getDeptList(String deptId, int page, int size);
    }
}
