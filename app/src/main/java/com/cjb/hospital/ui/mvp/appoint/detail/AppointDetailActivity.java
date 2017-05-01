package com.cjb.hospital.ui.mvp.appoint.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cjb.hospital.utils.DialogUtils;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.LogUtils;
import com.cjb.hospital.utils.SPUtils;
import com.cjb.hospital.utils.ToastUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;

public class AppointDetailActivity extends MvpBaseActivity<AppointDetailContract.AppointDetailPresenter> implements AppointDetailContract.AppointDetailView {

    @BindView(R.id.hospital_name_dialog)
    TextView hospitalNameDialog;
    @BindView(R.id.dept_name_dialog)
    TextView deptNameDialog;
    @BindView(R.id.doctor_name_dialog)
    TextView doctorNameDialog;
    @BindView(R.id.clinic_date_dialog)
    TextView clinicDateDialog;
    @BindView(R.id.location_dialog)
    TextView locationDialog;
    @BindView(R.id.price_dialog)
    TextView priceDialog;
    @BindView(R.id.patient_name_dialog)
    TextView patientNameDialog;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.btn_start_tv)
    TextView btnStartTv;
    @BindView(R.id.btn_timeout_tv)
    TextView btnTimeoutTv;
    @BindView(R.id.btn_complete_tv)
    TextView btnCompleteTv;
    @BindView(R.id.doctor_advice_edit)
    EditText doctorAdviceEdit;
    private String userid;
    private int role;
    private String appointId;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            appointId = params.getString(AppConstants.KEY_APPOINTMENT_ID, "");
            LogUtils.i("appointId:" + appointId);
        } else {
            finish();
        }
        role = (int) SPUtils.getSP(this, AppConstants.KEY_ROLE, ApiConstant.PATIENT);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter.getAppointmentDetail(appointId);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("btnCancel onclick");
                if(!TextUtils.isEmpty(appointId)){
                    DialogUtils.showMaterialDialog(getActivity(), "提示"
                            , "确认取消预约?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    mPresenter.cancelAppointment(appointId);
                                }
                            });
                }else{
                    ToastUtils.showMetrailToast(getActivity(),"该预约不存在");
                }
            }
        });
        btnStartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("btnStart onclick");
                if(!TextUtils.isEmpty(appointId)){
                    mPresenter.startAppointment(appointId);
                }else{
                    ToastUtils.showMetrailToast(getActivity(),"该预约不存在");
                }
            }
        });

        btnCompleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("btnComplete onclick");
                String doctorAdvice = doctorAdviceEdit.getText().toString();
                if(TextUtils.isEmpty(appointId)){
                    ToastUtils.showMetrailToast(getActivity(),"该预约不存在");
                }else if(TextUtils.isEmpty(doctorAdvice)) {
                    doctorAdviceEdit.setFocusable(true);
                    return;
                }else {
                    mPresenter.completeAppointment(appointId,doctorAdvice);
                }
            }
        });

        btnTimeoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("btnTimeout onclick");
                if(!TextUtils.isEmpty(appointId)){
                    DialogUtils.showMaterialDialog(getActivity(), "提示"
                            , "病人未按时就诊?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    mPresenter.timeOutappointment(appointId);
                                }
                            });
                }else{
                    ToastUtils.showMetrailToast(getActivity(),"该预约不存在");
                }
            }
        });
    }

    @Override
    public void getAppointDetailSucc(AppointmentDetail appointmentDetail) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        hospitalNameDialog.setText(appointmentDetail.getHospitalName());
        deptNameDialog.setText(appointmentDetail.getDeptName());
        doctorNameDialog.setText(appointmentDetail.getDoctorName());
        clinicDateDialog.setText(sdf.format(appointmentDetail.getClinicDate()));
        locationDialog.setText(appointmentDetail.getLocation());
        priceDialog.setText(appointmentDetail.getPrice() + "元");
        patientNameDialog.setText(appointmentDetail.getPatientName());
        showLayout(appointmentDetail.getStatus());
    }

    private void showLayout(int status) {
        switch (status) {
            //未处理预约
            case ApiConstant.APPOINT_UN_HANDLE:
                initToolBar("未诊断");
                showUnHandleView();
                break;
            //正在诊断
            case ApiConstant.APPOINT_HANDLE_ING:
                initToolBar("诊断中");
                showHandlingView();
                break;
            //已诊断
            case ApiConstant.APPOINT_COMPLETED:
                showCompleteView();
                initToolBar("已诊断");
                break;
        }
    }

    private void showCompleteView() {
        btnCancel.setVisibility(View.GONE);
        btnStartTv.setVisibility(View.GONE);
        btnTimeoutTv.setVisibility(View.GONE);
        btnCompleteTv.setVisibility(View.GONE);
        doctorAdviceEdit.setVisibility(View.VISIBLE);
        doctorAdviceEdit.setEnabled(false);
    }

    private void showHandlingView() {
        //医生
        if (role == ApiConstant.DOCTOR) {
            doctorAdviceEdit.setVisibility(View.VISIBLE);
            btnCompleteTv.setVisibility(View.VISIBLE);
        } else {//病人
            doctorAdviceEdit.setVisibility(View.GONE);
            btnCompleteTv.setVisibility(View.GONE);
        }
        btnCancel.setVisibility(View.GONE);
        btnStartTv.setVisibility(View.GONE);
        btnTimeoutTv.setVisibility(View.GONE);

    }

    private void showUnHandleView() {
        //医生
        if (role == ApiConstant.DOCTOR) {
            btnStartTv.setVisibility(View.VISIBLE);
            btnTimeoutTv.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
        } else {//病人
            btnStartTv.setVisibility(View.GONE);
            btnTimeoutTv.setVisibility(View.GONE);
            btnCancel.setVisibility(View.VISIBLE);
        }
        doctorAdviceEdit.setVisibility(View.GONE);
        btnCompleteTv.setVisibility(View.GONE);
    }

    @Override
    public void success(String msg) {
        ToastUtils.showMetrailToast(this,msg);
    }

    @Override
    public void error(String msg) {
        ToastUtils.showMetrailToast(this,msg);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_appoint_detail;
    }

    @Override
    protected AppointDetailContract.AppointDetailPresenter createPresenter() {
        return new AppointDetailPresenterImpl(this);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
