package com.cjb.hospital.aop;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.App;
import com.cjb.hospital.ui.mvp.user.login.LoginRegisterActivity;
import com.cjb.hospital.utils.SPUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by baixiaokang
 * 通过CheckLogin注解检查用户是否登陆注解，通过aop切片的方式在编译期间织入源代码中
 * 功能：检查用户是否登陆，未登录则提示登录，不会执行下面的逻辑
 */
@Aspect
public class CheckLoginAspect {

    @Pointcut("execution(@com.annotation.aspect.CheckLogin * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean isLogin = (boolean) SPUtils.getSP(App.getInstance(), AppConstants.KEY_IS_LOGIN,false);
        boolean isNeedLogin = (boolean) SPUtils.getSP(App.getInstance(), AppConstants.KEY_IS_NEED_LOGIN,false);
        if (isNeedLogin&&!isLogin) {
            final Activity curActivity = App.getInstance().getCurActivity();
            Snackbar.make(curActivity.getWindow().getDecorView(), "请先登录!", Snackbar.LENGTH_LONG)
                    .setAction("登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            TRouter.go(C.LOGIN);
                            //跳转至登录界面
                            Activity activity = curActivity;
                            Intent intent = new Intent(activity, LoginRegisterActivity.class);
                            activity.startActivity(intent);
                        }
                    }).show();
            return;
        }else{
            joinPoint.proceed();//执行原方法
        }
    }
}

