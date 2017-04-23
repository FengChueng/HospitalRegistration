package com.zyl.hospital.registration.ui.mvp.user.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.google.gson.Gson;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.BaseBean;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.MainActivity;
import com.zyl.hospital.registration.ui.mvp.user.login.next.CompleteInfoActivity;
import com.zyl.hospital.registration.utils.EncryptUtil;
import com.zyl.hospital.registration.utils.ResourceUtils;
import com.zyl.hospital.registration.utils.RouterUtils;
import com.zyl.hospital.registration.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterActivity extends MvpBaseActivity<LoginRegisterContract.LoginPresenter> implements LoginRegisterContract.LoginRegisterView {
    @BindView(R.id.login_register)
    MaterialLoginView loginRegister;

    MaterialDialog loginProgress;

    MaterialDialog registerProgress;

    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        ((DefaultLoginView) loginRegister.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout username, TextInputLayout password) {
                String name = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                if (name.isEmpty()) {
                    username.setErrorEnabled(true);
                    username.setError("用户名不可以为空");
                } else if (pass.isEmpty()) {
                    password.setErrorEnabled(true);
                    password.setError("密码不可以为空");
                } else {
                    selectRole(name, EncryptUtil.encryptUserPassword(pass));
                }
            }
        });

        ((DefaultRegisterView) loginRegister.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout username, TextInputLayout password, TextInputLayout repeatPassword) {
                String name = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String repeatPass = repeatPassword.getEditText().getText().toString();
                if (name.isEmpty()) {
                    username.setErrorEnabled(true);
                    username.setError("用户名不可以为空");
                } else if (pass.isEmpty()) {
                    password.setErrorEnabled(true);
                    password.setError("密码不可以为空");
                } else if (repeatPass.isEmpty()) {
                    repeatPassword.setErrorEnabled(true);
                    repeatPassword.setError("密码不可以为空");
                } else if (!pass.equals(repeatPass)) {
                    repeatPassword.setErrorEnabled(true);
                    repeatPassword.setError("两次密码输入不一致");
                } else {
                    mPresenter.register(name, EncryptUtil.encryptUserPassword(pass));
                }
            }
        });
    }

    private void selectRole(final String name, final String password) {
        new MaterialDialog.Builder(this)
                .title(R.string.select_role)
                .items(getRoleList())
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (which == 0) {
                            mPresenter.login(name, password, ApiConstant.PATIENT);
                        } else {
                            mPresenter.login(name, password, ApiConstant.DOCTOR);
                        }
                        return true;
                    }
                }).positiveText(R.string.role)
                .show();
    }

    private List<String> getRoleList() {
        List<String> roleList = new ArrayList<>();
        roleList.add("患者");
        roleList.add("医生");
        return roleList;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login_register;
    }

    @Override
    protected LoginRegisterContract.LoginPresenter createPresenter() {
        return new LoginRegisterPresenterImpl(this);
    }

    //implements LoginRegisterContract.LoginRegisterView
    @Override
    public void loginSuccess(BaseBean data) {
        if (data instanceof PatientBean) {
            SPUtils.putSP(getActivity(),
                    AppConstants.KEY_ROLE,
                    ApiConstant.PATIENT);
        } else {
            SPUtils.putSP(getActivity(),
                    AppConstants.KEY_ROLE,
                    ApiConstant.DOCTOR);
        }
        SPUtils.putSP(this, AppConstants.KEY_USER_JSON, new Gson().toJson(data));
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);
        //登录成功:跳转至主界面
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ,data);
        RouterUtils.gotoNext(this,MainActivity.class,bundle);
    }

    @Override
    public void loginError(String msg) {
        new SuperToast(this)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(ResourceUtils.getColor(R.color.load_red))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @Override
    public void registerSuccess(PatientBean data) {
        SPUtils.putSP(getActivity(),
                AppConstants.KEY_ROLE,
                ApiConstant.PATIENT);
        //存储userjson
        SPUtils.putSP(this, AppConstants.KEY_USER_JSON, new Gson().toJson(data));
        //登录成功:跳转至主界面
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ, data);
        RouterUtils.gotoNext(this, CompleteInfoActivity.class, bundle);
    }

    @Override
    public void registerError(String msg) {
        new SuperToast(this)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(ResourceUtils.getColor(R.color.load_red))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
