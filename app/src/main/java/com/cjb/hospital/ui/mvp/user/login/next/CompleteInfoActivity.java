package com.cjb.hospital.ui.mvp.user.login.next;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.ResourceUtils;
import com.cjb.hospital.utils.ToastUtils;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.google.gson.Gson;
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.PatientBean;
import com.cjb.hospital.utils.SPUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zyl.hospital.hospital.R.id.register_next_name_auto;
import static com.zyl.hospital.hospital.R.string.sex;


public class CompleteInfoActivity extends MvpBaseActivity<CompleteInfoContract.CompleteInfoPresenter> implements CompleteInfoContract.CompleteInfoView,DatePickerDialog.OnDateSetListener{
    private static final String TAG = CompleteInfoActivity.class.getSimpleName();
    @BindView(R.id.register_next_sex)
    TextView registerNextSex;
    @BindView(R.id.register_next_birthday)
    TextView registerNextBirthday;
    @BindView(R.id.register_next_sub)
    TextView registerNextSub;
    @BindView(register_next_name_auto)
    AutoCompleteTextView registerNextNameAutocomplete;
    private String userId = "";

    MaterialDialog progress;
    private PatientBean patientBean;
    private int sexParam;
    private long birthday;

    private List<String> getSexList() {
        List<String> sexlist = new ArrayList<>();
        sexlist.add("男");
        sexlist.add("女");
        return sexlist;
    }

    public void selectSex() {
        new MaterialDialog.Builder(this)
                .title(R.string.register_next_sex_choose)
                .items(getSexList())
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        registerNextSex.setText(text);
                        sexParam = which == 0? ApiConstant.SEX_MALE:ApiConstant.SEX_FEMALE;
                        return true;
                    }
                }).positiveText(sex)
                .show();
    }

    @Override
    protected CompleteInfoContract.CompleteInfoPresenter createPresenter() {
        return new CompleteInfoPresenterImpl(this);
    }

    @Override
    protected void initParams(Bundle params) {
        if(params!=null){
            patientBean = (PatientBean)params.getSerializable(AppConstants.KEY_USER_OBJ);
        }else{
            ToastUtils.showMetrailToast(this,"未知异常");
            finish();
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register_next;
    }

    @Override
    public void completeinfoSucc(PatientBean userEntity) {
        //存储userjson
        SPUtils.putSP(this, AppConstants.KEY_USER_JSON, new Gson().toJson(userEntity));
        //登录成功:跳转至主界面
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);
        SPUtils.putSP(this, AppConstants.KEY_USER_ID, userEntity.getPatientAccount());
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ, userEntity);
        //跳转
        //RouterUtils.gotoNextNoData(this,MainActivity.class);
        finish();
    }

    @Override
    public void completeinfoError(String msg) {
        new SuperToast(CompleteInfoActivity.this)
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

    @OnClick({R.id.register_next_sex, R.id.register_next_birthday, R.id.register_next_sub})
    public void onClick(View view) {
        String name = registerNextNameAutocomplete.getText().toString();
        switch (view.getId()) {
            case R.id.register_next_sex:
                selectSex();
                break;
            case R.id.register_next_birthday:
                selectBirthDay();
                break;
            case R.id.register_next_sub:
                if (TextUtils.isEmpty(name)) {
                    registerNextNameAutocomplete.setFocusable(true);
                    ToastUtils.showMetrailToast(this,"请输入姓名");
                    return;
                }
                if (sex == 0) {
                    registerNextSex.setFocusable(true);
                    ToastUtils.showMetrailToast(this,"请选择性别");
                    return;
                }

                if(birthday == 0){
                    registerNextBirthday.setFocusable(true);
                    ToastUtils.showMetrailToast(this,"请选择生日");
                    return;
                }
                mPresenter.completeinfo(patientBean.getPatientAccount(),name,sexParam, birthday);
                break;
        }
    }

    private void selectBirthDay() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog date = new DatePickerDialog.Builder(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))
                .build();
        date.show(getSupportFragmentManager(),TAG);
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = new java.util.GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        birthday = cal.getTimeInMillis();
        registerNextBirthday.setText(DateFormat.getDateFormat(this).format(cal.getTime()));
    }
}
