package com.cjb.hospital.ui.mvp.user.login.next;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.bean.PatientBean;

import rx.Observable;


/**
 * 契约类
 */
public interface CompleteInfoContract {
    abstract class CompleteInfoPresenter extends BasePresenter {
        abstract void completeinfo(String account,String name,int sex,long birthday);
    }

    interface CompleteInfoView extends BaseView {
        void completeinfoSucc(PatientBean userEntity);
        void completeinfoError(String msg);
    }

    interface CompleteInfoModel extends BaseModel {
        Observable<ResponseEntity<PatientBean>> completeinfo(String account, String name, int sex, long birthday);

//        PatientBean getFromLocal(Context context);
    }
}
