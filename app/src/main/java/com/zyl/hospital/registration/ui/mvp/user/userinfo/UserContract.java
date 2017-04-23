package com.zyl.hospital.registration.ui.mvp.user.userinfo;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.ResultEntity;

import retrofit2.http.GET;
import rx.Observable;


/**
 * 契约类
 */
public interface UserContract {
    abstract class UserPresenter extends BasePresenter {
        abstract void getUserInfo(String mobile, String password);
    }

    interface UserView extends BaseView {
        void loadUserInfo(ResultEntity<?> userEntity);
        void loadError();
    }

    interface UserModel extends BaseModel {
        @GET("")
        Observable<ResultEntity<?>> getUserInfo(String mobile, String password);
    }
}
