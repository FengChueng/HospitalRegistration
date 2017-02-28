package com.zyl.hospital.registration.ui.mvp.user.userinfo;

/**
 * Created by Administrator on 2017/2/26.
 */

public class UserPresenterImpl extends UserContract.UserPresenter {
    UserContract.UserView mUserView;
    UserModelImpl mLoginModel;
    public UserPresenterImpl(UserContract.UserView mUserView){
        this.mUserView = mUserView;
        mLoginModel = new UserModelImpl();
    }
    @Override
    void getUserInfo(String mobile, String password) {
        
    }
}
