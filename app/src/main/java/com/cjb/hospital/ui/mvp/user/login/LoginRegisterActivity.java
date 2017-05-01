package com.cjb.hospital.ui.mvp.user.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.ui.mvp.user.login.next.CompleteInfoActivity;
import com.cjb.hospital.utils.EncryptUtil;
import com.cjb.hospital.utils.ResourceUtils;
import com.cjb.hospital.utils.RouterUtils;
import com.cjb.hospital.utils.SPUtils;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.google.gson.Gson;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.bean.BaseBean;
import com.cjb.hospital.bean.PatientBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterActivity extends MvpBaseActivity<LoginRegisterContract.LoginPresenter> implements LoginRegisterContract.LoginRegisterView {
    @BindView(R.id.login_register)
    MaterialLoginView loginRegister;

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
            SPUtils.putSP(this,AppConstants.KEY_USER_ID,((PatientBean) data).getPatientAccount());
        } else if(data instanceof DoctorBean){
            SPUtils.putSP(getActivity(),
                    AppConstants.KEY_ROLE,
                    ApiConstant.DOCTOR);
            SPUtils.putSP(this,AppConstants.KEY_USER_ID,((DoctorBean) data).getDoctorAccount());
        }
        SPUtils.putSP(this, AppConstants.KEY_USER_JSON, new Gson().toJson(data));
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);

        //登录成功:跳转至主界面
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ,data);
        //RouterUtils.gotoNext(this,MainActivity.class,bundle);
        finish();
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
        //登录成功:填写详细信息
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);
        SPUtils.putSP(this,AppConstants.KEY_USER_ID,data.getPatientAccount());
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ, data);
        RouterUtils.gotoNext(this, CompleteInfoActivity.class, bundle);
        finish();
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
