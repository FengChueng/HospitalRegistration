package com.zyl.hospital.registration.ui.mvp.user.login.next;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.google.gson.Gson;
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.MvpBaseActivity;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.MainActivity;
import com.zyl.hospital.registration.utils.ResourceUtils;
import com.zyl.hospital.registration.utils.RouterUtils;
import com.zyl.hospital.registration.utils.SPUtils;
import com.zyl.hospital.registration.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.data;
import static com.zyl.hospital.registration.R.id.register_next_name_auto;


public class CompleteInfoActivity extends MvpBaseActivity<CompleteInfoContract.CompleteInfoPresenter> implements CompleteInfoContract.CompleteInfoView,DatePickerDialog.OnDateSetListener{
    private static final String TAG = CompleteInfoActivity.class.getSimpleName();
    @BindView(R.id.register_next_sex)
    TextView registerNextSex;
    @BindView(R.id.register_next_birthday)
    TextView registerNextBirthday;
    @BindView(R.id.register_next_sub)
    TextView registerNextSub;
    @BindView(register_next_name_auto)
    AutoCompleteTextView registerNextPhoneAutocomplete;
    private String userId = "";

    MaterialDialog progress;
    private PatientBean patientBean;
    private int sexParam;
    private long birtyDay;

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
                }).positiveText(R.string.sex)
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
        return R.layout.register_next_activity;
    }

    @Override
    public void completeinfoSucc(PatientBean userEntity) {
        //存储userjson
        SPUtils.putSP(this, AppConstants.KEY_USER_JSON, new Gson().toJson(userEntity));
        //登录成功:跳转至主界面
        SPUtils.putSP(this, AppConstants.KEY_IS_FIRST_LOGIN, false);
        SPUtils.putSP(this, AppConstants.KEY_IS_LOGIN, true);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.KEY_USER_OBJ, userEntity);
        //跳转
        RouterUtils.gotoNextNoData(this,MainActivity.class);
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
        String name = registerNextPhoneAutocomplete.getText().toString();
        switch (view.getId()) {
            case R.id.register_next_sex:
                selectSex();
                break;
            case R.id.register_next_birthday:
                selectBirthDay();
                break;
            case R.id.register_next_sub:
                mPresenter.completeinfo(name,sexParam,birtyDay);
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
        birtyDay = cal.getTimeInMillis();
        registerNextBirthday.setText("Date set: " + DateFormat.getDateFormat(this).format(cal.getTime()));
    }
}
