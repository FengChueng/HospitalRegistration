package com.zyl.hospital.registration.ui.mvp.user.login;


import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginRegisterRegisterModelImpl implements LoginRegisterContract.LoginRegisterModel {
    //LoginRegisterContract.LoginRegisterModel service = Api.getInstance().createService(LoginRegisterContract.LoginRegisterModel.class);
    @Override
    public Observable<ResultEntity<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password) {
        // return service.login(mobile,password);
        return null;
    }

    @Override
    public Observable<ResultEntity<UserEntity>> register(@Query("mobile") String mobile, @Query("password") String password) {
        return null;
    }
}
