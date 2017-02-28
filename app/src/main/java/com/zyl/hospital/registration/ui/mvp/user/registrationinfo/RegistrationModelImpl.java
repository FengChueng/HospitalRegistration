package com.zyl.hospital.registration.ui.mvp.user.registrationinfo;


import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.bean.UserEntity;
import com.zyl.hospital.registration.http.api.Api;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/26.
 */

public class RegistrationModelImpl implements RegistrationContract.RegistrationModel {
    RegistrationContract.RegistrationModel service = Api.getInstance().createService(RegistrationContract.RegistrationModel.class);
    @Override
    public Observable<ResultEntity<UserEntity>> getRegistration(@Query("mobile") String mobile, @Query("password") String password) {
        return service.getRegistration(mobile,password);
    }
}
