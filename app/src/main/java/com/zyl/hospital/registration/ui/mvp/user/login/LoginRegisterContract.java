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
public interface LoginRegisterContract {
    abstract class LoginPresenter extends BasePresenter {
        abstract void login(String mobile,String password);
        abstract void register(String mobile, String password);
    }

    interface LoginRegisterView extends BaseView {
        void loginSuccess(ResultEntity<UserEntity> userEntity);
        void loginError();

        void registerSuccess(ResultEntity<UserEntity> userEntity);
        void registerError();
    }

    interface LoginRegisterModel extends BaseModel {
        @GET("")
        Observable<ResultEntity<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password);

        @GET("")
        Observable<ResultEntity<UserEntity>> register(@Query("mobile") String mobile, @Query("password") String password);
    }
}
