package com.zyl.hospital.registration.ui.mvp.user.login;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 契约类
 */
public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter {
        abstract void login(String mobile,String password);
    }

    interface LoginView extends BaseView {
        void loginSuccess(ResultEntity<UserEntity> userEntity);
        void loginError();
    }

    interface LoginModel extends BaseModel {
        @GET("")
        Observable<ResultEntity<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password);
    }
}
