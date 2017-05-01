package com.cjb.hospital.ui.mvp.user.userinfo;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.bean.BaseBean;

import rx.Observable;


/**
 * 契约类
 */
public interface UserInfoContract {
    abstract class UserPresenter extends BasePresenter {
        abstract void getUserInfo(String mobile, String password);
    }

    interface UserView extends BaseView {
        void loadUserInfo(ResponseEntity<?> userEntity);
        void loadError();
    }

    interface UserModel extends BaseModel {
        Observable<ResponseEntity<BaseBean>> getUserInfo(String mobile, String password);
    }
}
