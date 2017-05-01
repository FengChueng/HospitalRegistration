package com.cjb.hospital.ui.mvp.appoint.hospital;

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.bean.ResponseEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface HospitalContract {
    abstract class HospitalPresenter extends BasePresenter<BaseView> {
        abstract void getHospitalList(int page,int size);
    }

    interface HospitalView extends BaseView{
        void getHospitalSucc(List<Hospital> hospitals);
        void getHospitalError(String msg);
    }

    interface HospitalModel extends BaseModel{
        Observable<ResponseEntity<List<Hospital>>> getHospitalList(int page, int size);
    }

}
