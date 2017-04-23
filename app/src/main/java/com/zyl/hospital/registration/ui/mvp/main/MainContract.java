package com.zyl.hospital.registration.ui.mvp.main;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;

import rx.Observable;


/**
 * 契约类
 */
public interface MainContract {
    abstract class MainPresenter extends BasePresenter {
        abstract void login(String mobile,String password,int role);
        abstract void register(String mobile, String password);
    }

    interface MainView extends BaseView {
        void loginSuccess(ResultEntity<?> userEntity);
        void loginError(String msg);

        void registerSuccess(ResultEntity<PatientBean> userEntity);
        void registerError(String msg);
    }

    interface LoginRegisterModel extends BaseModel {
        Observable<ResultEntity<?>> login(String mobile, String password, int role);

        Observable<ResultEntity<PatientBean>> register(String mobile, String password);
    }
}
