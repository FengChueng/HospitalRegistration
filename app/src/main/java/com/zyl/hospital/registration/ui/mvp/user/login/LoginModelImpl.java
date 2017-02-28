package com.zyl.hospital.registration.ui.mvp.user.login;


import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;
import com.zyl.hospital.registration.http.api.Api;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginModelImpl implements LoginContract.LoginModel {
    LoginContract.LoginModel service = Api.getInstance().createService(LoginContract.LoginModel.class);
    @Override
    public Observable<ResultEntity<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password) {
        return service.login(mobile,password);
    }
}
