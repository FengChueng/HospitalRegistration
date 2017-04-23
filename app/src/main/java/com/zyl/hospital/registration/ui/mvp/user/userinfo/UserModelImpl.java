package com.zyl.hospital.registration.ui.mvp.user.userinfo;


import com.zyl.hospital.registration.bean.ResultEntity;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class UserModelImpl implements UserContract.UserModel {
//    UserContract.UserModel service = Api.getInstance().createService(UserContract.UserModel.class);
    @Override
    public Observable<ResultEntity<?>> getUserInfo(String mobile, String password) {
//        return service.getUserInfo(mobile,password);
        return null;
    }
}
