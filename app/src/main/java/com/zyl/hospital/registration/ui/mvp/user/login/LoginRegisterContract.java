package com.zyl.hospital.registration.ui.mvp.user.login;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.BaseBean;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;

import rx.Observable;


/**
 * 契约类
 */
public interface LoginRegisterContract {
    abstract class LoginPresenter extends BasePresenter {
        abstract void login(String mobile,String password,int role);
        abstract void register(String mobile, String password);
    }

    interface LoginRegisterView extends BaseView {
        void loginSuccess(BaseBean baseBean);
        void loginError(String msg);

        void registerSuccess(PatientBean userEntity);
        void registerError(String msg);
    }

    interface LoginRegisterModel extends BaseModel {
        Observable<ResponseEntity<?>> login(String mobile, String password, int role);

        Observable<ResponseEntity<PatientBean>> register(String mobile, String password);
    }
}
