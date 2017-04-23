package com.zyl.hospital.registration.ui.mvp.user.login.next;

/**
 * Created by Administrator on 2017/2/26.
 */

import android.content.Context;

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;

import rx.Observable;


/**
 * 契约类
 */
public interface CompleteInfoContract {
    abstract class CompleteInfoPresenter extends BasePresenter {
        abstract void completeinfo(String name,int sex,long birthday);
    }

    interface CompleteInfoView extends BaseView {
        void completeinfoSucc(PatientBean userEntity);
        void completeinfoError(String msg);
    }

    interface CompleteInfoModel extends BaseModel {
        Observable<ResultEntity<PatientBean>> completeinfo(String account, String name, int sex, long birthday);

        PatientBean getFromLocal(Context context);
    }
}
