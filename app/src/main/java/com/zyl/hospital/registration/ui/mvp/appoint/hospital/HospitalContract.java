package com.zyl.hospital.registration.ui.mvp.appoint.hospital;

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.bean.ResultEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface HospitalContract {
    abstract class HospitalPresenter extends BasePresenter<BaseView>{
        abstract void getHospitalList(int page,int size);
    }

    interface HospitalView extends BaseView{
        void getHospitalSucc(List<Hospital> hospitals);
        void getHospitalError(String msg);
    }

    interface HospitalModel extends BaseModel{
        Observable<ResultEntity<List<Hospital>>> getHospitalList(int page,int size);
    }

}
