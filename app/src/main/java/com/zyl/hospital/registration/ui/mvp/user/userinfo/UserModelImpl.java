package com.zyl.hospital.registration.ui.mvp.user.userinfo;


import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;
import com.zyl.hospital.registration.http.api.Api;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class UserModelImpl implements UserContract.UserModel {
    UserContract.UserModel service = Api.getInstance().createService(UserContract.UserModel.class);
    @Override
    public Observable<ResultEntity<UserEntity>> getUserInfo(@Query("mobile") String mobile, @Query("password") String password) {
        return service.getUserInfo(mobile,password);
    }
}
