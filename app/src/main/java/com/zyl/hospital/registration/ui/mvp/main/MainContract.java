package com.zyl.hospital.registration.ui.mvp.main;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;

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
        void loginSuccess(ResponseEntity<?> userEntity);
        void loginError(String msg);

        void registerSuccess(ResponseEntity<PatientBean> userEntity);
        void registerError(String msg);
    }

    interface LoginRegisterModel extends BaseModel {
        Observable<ResponseEntity<?>> login(String mobile, String password, int role);

        Observable<ResponseEntity<PatientBean>> register(String mobile, String password);
    }
}
