package com.cjb.hospital.ui.mvp.user.login;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.BaseBean;
import com.cjb.hospital.bean.PatientBean;

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
