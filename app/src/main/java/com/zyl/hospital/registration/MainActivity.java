package com.zyl.hospital.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.annotation.aspect.CheckLogin;
import com.annotation.aspect.SingleClick;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyl.hospital.registration.base.BaseActivity;
import com.zyl.hospital.registration.ui.mvp.user.userinfo.UserInfoActivity;
import com.zyl.hospital.registration.utils.LogUtils;
import com.zyl.hospital.registration.utils.SPUtils;
import com.zyl.hospital.registration.utils.ToastUtils;

import okhttp3.Call;

public class MainActivity extends BaseActivity {

    private EditText nameEditText;
    private EditText pwdEditText;

    @Override
    protected void initParams(Bundle params) {
        SPUtils.putSP(this,"isLogin",false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        nameEditText = (EditText) findViewById(R.id.name);
        pwdEditText = (EditText) findViewById(R.id.pwd);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @SingleClick
    public void checkLogin(View view) {
        LogUtils.d("checkLogin");
        check();
    }

    @CheckLogin
    private void check() {
        LogUtils.d("check");
    }

    @SingleClick
    public void user_info(View view) {
        this.startActivity(new Intent(this, UserInfoActivity.class));
    }

    public void login(View view) {
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();

        if(TextUtils.isEmpty(name)){
            ToastUtils.show(this,"账号不能为空");
            nameEditText.setFocusable(true);
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastUtils.show(this,"密码不能为空");
            pwdEditText.setFocusable(true);
            return;
        }
        String url = "http://192.168.1.102:9090/account/login?name="+name+"&pwd="+pwd;
        LogUtils.i("url"+url);
        try {
            OkHttpUtils.get().url(url).build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.e(e.getMessage());
                            ToastUtils.show(MainActivity.this,"登录失败:"+e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.e(response);
                            ToastUtils.show(MainActivity.this,"登录成功");
                        }
                    });
        }catch (Exception e){
            LogUtils.e("url:"+url+"e:"+e.toString());
        }
    }
}
