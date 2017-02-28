package com.zyl.hospital.registration.ui.mvp.user.register;


import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;
import com.zyl.hospital.registration.http.api.Api;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class RegisterModelImpl implements RegisterContract.RegisterModel {
    RegisterContract.RegisterModel service = Api.getInstance().createService(RegisterContract.RegisterModel.class);
    @Override
    public Observable<ResultEntity<UserEntity>> register(@Query("mobile") String mobile, @Query("password") String password) {
        return service.register(mobile,password);
    }
}
