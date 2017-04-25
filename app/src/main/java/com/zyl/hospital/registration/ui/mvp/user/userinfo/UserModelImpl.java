package com.zyl.hospital.registration.ui.mvp.user.userinfo;


import com.zyl.hospital.registration.bean.ResponseEntity;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class UserModelImpl implements UserInfoContract.UserModel {
//    UserInfoContract.UserModel service = Api.getInstance().createService(UserInfoContract.UserModel.class);
    @Override
    public Observable<ResponseEntity<?>> getUserInfo(String mobile, String password) {
//        return service.getUserInfo(mobile,password);
        return null;
    }
}
