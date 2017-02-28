package com.zyl.hospital.registration.ui.mvp.user.userinfo;

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
public interface UserContract {
    abstract class UserPresenter extends BasePresenter {
        abstract void getUserInfo(String mobile, String password);
    }

    interface UserView extends BaseView {
        void loadUserInfo(ResultEntity<UserEntity> userEntity);
        void loadError();
    }

    interface UserModel extends BaseModel {
        @GET("")
        Observable<ResultEntity<UserEntity>> getUserInfo(@Query("mobile") String mobile, @Query("password") String password);
    }
}
