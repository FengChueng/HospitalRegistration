package com.zyl.hospital.registration.ui.mvp.appoint.doctor;

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.ResultEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface DoctorContract {
    abstract class DoctorPresenter extends BasePresenter<BaseView>{
        abstract void getDoctor(String deptId, int page, int size);
    }

    interface DoctorView extends BaseView{
        void getDoctorListSucc(List<DoctorBean> doctorBeans);
        void getDoctorListError(String msg);
    }

    interface DoctorModel extends BaseModel{
        Observable<ResultEntity<List<DoctorBean>>> getDeptList(String deptId, int page, int size);
    }
}
