package com.cjb.hospital.ui.mvp.user.userinfo;

/**
 * Created by Administrator on 2017/2/26.
 */

public class UserPresenterImpl extends UserInfoContract.UserPresenter {
    UserInfoContract.UserView mUserView;
    UserModelImpl mLoginModel;
    public UserPresenterImpl(UserInfoContract.UserView mUserView){
        this.mUserView = mUserView;
        mLoginModel = new UserModelImpl();
    }
    @Override
    void getUserInfo(String mobile, String password) {
        
    }
}
