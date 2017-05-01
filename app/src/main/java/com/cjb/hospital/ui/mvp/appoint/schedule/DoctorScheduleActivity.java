package com.cjb.hospital.ui.mvp.appoint.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.annotation.aspect.CheckLogin;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cjb.hospital.base.MvpBaseActivity;
import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.DoctorSchedule;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.ImageLoader;
import com.cjb.hospital.utils.SPUtils;
import com.cjb.hospital.utils.ToastUtils;
import com.cjb.hospital.widget.CircleImageView;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.adapter.DoctorScheduleAdapter;
import com.cjb.hospital.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoctorScheduleActivity extends MvpBaseActivity<DoctorScheduleContract.DoctorSchedulePresenter> implements DoctorScheduleContract.DoctorScheduleView {

    @BindView(R.id.doctor_portrait)
    CircleImageView doctorPortrait;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.doctor_info_ll)
    LinearLayout doctorInfoLl;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    private CollapsingToolbarLayoutState state;
    private DoctorScheduleAdapter adapter;
    private List<DoctorSchedule> doctorSchedules;
    private String userid;
    private DoctorBean mDoctorBean;
    private int level;
    private String hospitalId;
    private String deptId;
    private DoctorSchedule doctorSchedule;
    private MaterialDialog materialDialog;
    private View dialogCustom;
    private int role;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    private String doctorId;
    private int page = 0;
    private int size = 30;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            //doctorId = (String) params.get(AppConstants.KEY_DOCTOR_ID);
            //mPresenter.getDoctorSchedule(doctorId, page, size);

            mDoctorBean = (DoctorBean) params.get(AppConstants.KEY_DOCTOR_OBJ);
            LogUtils.i(mDoctorBean.getDoctorSchedules().get(0).getDoctorScheduleId());
        } else {
            finish();
        }

        hospitalId = (String) SPUtils.getSP(this, AppConstants.KEY_HOSPITAL_ID, "");
        deptId = (String) SPUtils.getSP(this, AppConstants.KEY_DEPT_ID, "");
        userid = (String) SPUtils.getSP(this, AppConstants.KEY_USER_ID, "18380586504");

        role = (int) SPUtils.getSP(this, AppConstants.KEY_ROLE, ApiConstant.PATIENT);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBarNoBack("医生详情");
        collapsing.setTitleEnabled(false);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        doctorInfoLl.setVisibility(View.GONE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            doctorInfoLl.setVisibility(View.VISIBLE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

        dialogCustom = LayoutInflater.from(this).inflate(R.layout.appoint_info_dialog_layout, null);

        materialDialog = new MaterialDialog.Builder(getActivity())
                .title("预约信息")
                .customView(dialogCustom, true)
                .positiveText("确认")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (doctorSchedule == null) {
                            ToastUtils.showMetrailToast(getActivity(), "未知错误");
                            materialDialog.dismiss();
                            return;
                        }
                        mPresenter.makeAppointment(hospitalId, deptId, userid, mDoctorBean.getDoctorAccount(), doctorSchedule.getDoctorScheduleId());
                    }
                })
                .negativeText("取消")
                .build();

        level = mDoctorBean.getLevel();
        doctorName.setText(mDoctorBean.getRealName());
        info.setText(mDoctorBean.getInfo());
        ImageLoader.getIns(this).load(ApiConstant.API_SERVER_URL + mDoctorBean.getPortraint(),
                doctorPortrait, R.mipmap.portraint_default, R.mipmap.portraint_default);
        if (mDoctorBean.getDoctorSchedules() == null) {
            ToastUtils.showMetrailToast(this, "该医生无工作安排");
            return;
        }

        doctorSchedules = new ArrayList<>();
        doctorSchedules.addAll(mDoctorBean.getDoctorSchedules());
        adapter = new DoctorScheduleAdapter(this, doctorSchedules);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                SPUtils.putSP(getActivity(),AppConstants.KEY_IS_NEED_LOGIN,true);
                getAppointInfo((DoctorSchedule) baseQuickAdapter.getItem(position));
            }
        });
    }

    @CheckLogin
    private void getAppointInfo(DoctorSchedule doctorSchedule){
        if(role == ApiConstant.DOCTOR){
            ToastUtils.showMetrailToast(getActivity(),"您是医生,不可预约");
            return;
        }
        LogUtils.i(hospitalId+","+deptId+","+userid+","+mDoctorBean.getDoctorAccount()+","+doctorSchedule.getDoctorScheduleId());
        this.doctorSchedule = doctorSchedule;
        mPresenter.getAppointmentInfo(hospitalId, deptId, userid, mDoctorBean.getDoctorAccount(), doctorSchedule.getDoctorScheduleId());
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_doctor_schedule;
    }

    @Override
    protected DoctorScheduleContract.DoctorSchedulePresenter createPresenter() {
        return new DoctorSchedulePresenterImpl(this);
    }

    @Override
    public void getDoctorScheduleSucc(DoctorBean doctorBean) {
        doctorSchedules.addAll(mDoctorBean.getDoctorSchedules());
        adapter.addData(doctorSchedules);
    }

    @Override
    public void getDoctorScheduleError(String msg) {
        ToastUtils.showMetrailToast(this, msg);
    }

    @Override
    public void noData(String msg) {
        ToastUtils.showMetrailToast(this, msg);
    }

    @Override
    public void makeAppointmentSucc(Appointment appointment) {
        ToastUtils.showMetrailToast(this, "预约成功");
    }

    @Override
    public void makeAppointmentError(String msg) {
        ToastUtils.showMetrailToast(this, "预约失败" + msg);
    }

    @Override
    public void getAppointmentInfoSucc(AppointmentDetail appointmentDetail) {
        TextView hospitalNameDialog = (TextView) dialogCustom.findViewById(R.id.hospital_name_dialog);
        TextView deptNameDialog = (TextView) dialogCustom.findViewById(R.id.dept_name_dialog);
        TextView doctorNameDialog = (TextView) dialogCustom.findViewById(R.id.doctor_name_dialog);
        TextView clinicDateDialog = (TextView) dialogCustom.findViewById(R.id.clinic_date_dialog);
        TextView locationDialog = (TextView) dialogCustom.findViewById(R.id.location_dialog);
        TextView priceDialog = (TextView) dialogCustom.findViewById(R.id.price_dialog);
        TextView patientNameDialog = (TextView) dialogCustom.findViewById(R.id.patient_name_dialog);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        hospitalNameDialog.setText(appointmentDetail.getHospitalName());
        deptNameDialog.setText(appointmentDetail.getDeptName());
        doctorNameDialog.setText(appointmentDetail.getDoctorName());
        clinicDateDialog.setText(sdf.format(appointmentDetail.getClinicDate()));
        locationDialog.setText(appointmentDetail.getLocation());
        priceDialog.setText(appointmentDetail.getPrice() + "元");
        patientNameDialog.setText(appointmentDetail.getPatientName());
        materialDialog.show();
    }

    @Override
    public void getAppointmentInfoError(String msg) {
        materialDialog.dismiss();
        ToastUtils.showMetrailToast(this, "获取预约信息失败");
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
