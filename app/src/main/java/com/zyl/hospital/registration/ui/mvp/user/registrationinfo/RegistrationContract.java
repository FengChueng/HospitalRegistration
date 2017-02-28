package com.zyl.hospital.registration.ui.mvp.user.registrationinfo;

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
 * 挂号
 */
public interface RegistrationContract {
    abstract class RegistrationPresenter extends BasePresenter {
        //
        abstract void getRegistration(String mobile, String password);
    }

    interface RegistrationView extends BaseView {
        void loadRegistration(ResultEntity<UserEntity> userEntity);
        void loadError();
    }

    interface RegistrationModel extends BaseModel {
        @GET("")
        Observable<ResultEntity<UserEntity>> getRegistration(@Query("mobile") String mobile, @Query("password") String password);
    }
}
