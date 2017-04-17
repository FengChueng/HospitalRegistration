package com.zyl.hospital.registration.data.remote.repository.impl;

import android.content.Context;

import com.zyl.hospital.registration.data.remote.entity.User;
import com.zyl.hospital.registration.data.remote.repository.IResult;
import com.zyl.hospital.registration.data.remote.repository.UserRepository;
import com.zyl.hospital.registration.rx.RxSchedulers;
import com.zyl.hospital.registration.rx.RxSubscriber;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by cjb
 */


public class UserRepositoryImpl implements UserRepository {

    @Override
    public void requestSMSCode(Context context,String mobilePhone, final IResult iResult) {
        BmobSMS.requestSMSCodeObservable(mobilePhone,"")
                .compose(RxSchedulers.<Integer>io_main())
                .subscribe(new RxSubscriber<Integer>(context) {
                    @Override
                    protected void _onNext(Integer integer) {
                        iResult.onSuccess(integer.toString());
                    }

                    @Override
                    protected void _onError(String message) {
                        iResult.onFail(message);
                    }
                });
    }

    @Override
    public void signOrLoginByMobilePhone(Context context,String mobilePhone, String code, final IResult iResult) {
        BmobUser.signOrLoginByMobilePhone(mobilePhone, code, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user!=null){//登录成功
                    iResult.onSuccess(user);
                }else{
                    iResult.onFail(e);
                }
            }
        });
    }

    @Override
    public void signOrLogin(Context context,User user, String code, final IResult iResult) {
        user.signOrLogin(code, new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){//注册或者登录成功
                    iResult.onSuccess(user);
                }else{
                    iResult.onFail(e);
                }
            }
        });
    }

    @Override
    public void resetPasswordBySMSCode(Context context, String code, String password, final IResult iResult) {
        BmobUser.resetPasswordBySMSCodeObservable(code,password)
                .compose(RxSchedulers.<Void>io_main())
                .subscribe(new RxSubscriber<Void>(context) {
                    @Override
                    protected void _onNext(Void aVoid) {
                        iResult.onSuccess(aVoid);
                    }

                    @Override
                    protected void _onError(String message) {
                        iResult.onFail(message);
                    }
                });
    }

    @Override
    public void logout(Context context,IResult iResult) {
        BmobUser.logOut();
    }

    @Override
    public BmobUser getCurrentUser() {
        return BmobUser.getCurrentUser();
    }
}
