package com.zyl.hospital.registration.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.annotation.aspect.CheckLogin;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.TabSelectionInterceptor;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.base.BaseActivity;
import com.zyl.hospital.registration.bean.BaseBean;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.constants.AppConstants;
import com.zyl.hospital.registration.ui.fragment.AppointmentManagerFragmentDoctor;
import com.zyl.hospital.registration.ui.fragment.AppointmentManagerFragmentPatient;
import com.zyl.hospital.registration.ui.fragment.NewsFragment;
import com.zyl.hospital.registration.ui.fragment.PersonalFragmentDoctor;
import com.zyl.hospital.registration.ui.fragment.PersonalFragmentPatient;
import com.zyl.hospital.registration.ui.mvp.appoint.hospital.HospitalFragment;
import com.zyl.hospital.registration.utils.SPUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    /* 标题栏 */
    private static final String[] titles = {"健康资讯", "预约挂号","预约管理","个人中心"};
    /* fragment tag */
    private static final String[] FRAGMENT_TAGS = {"news","appointment","appointment_manager","personal"};
    private static final int[] orderTypes = {AppConstants.NEWS_FRAGMENT, AppConstants.APPOINTMENT_FRAGMENT,AppConstants.APPOINTMENT_MANAGER_FRAGMENT,AppConstants.PERSONAL_FRAGMENT};

    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    /* fragment管理器 */
    private FragmentManager fragmentManager;
    //新闻界面Fragment
    private NewsFragment newsFragment;
    //预约界面
    private HospitalFragment hospitalFragment;

    //医生界面Fragment
    //预约管理界面
    private AppointmentManagerFragmentDoctor appointmentFragmentDoctor;
    private PersonalFragmentDoctor personalFragmentDoctor;
    //病人界面Fragment
    private AppointmentManagerFragmentPatient appointmentFragmentPatient;
    private PersonalFragmentPatient personalFragmentPatient;
    /* fragment索引键名 */
    private static final String FRAGMENT_TAG_KEY = "tag_name";
    /* 当前显示的fragment索引 */
    private int index = 0;
    //用户当前角色
    private int role = ApiConstant.PATIENT;

    @Override
    protected void initParams(Bundle params) {
        if (params != null) {
            //用户信息
            BaseBean baseBean = (BaseBean) params.get(AppConstants.KEY_USER_OBJ);
            if (baseBean instanceof DoctorBean) {
                role = ApiConstant.DOCTOR;
            }else if (baseBean instanceof PatientBean){
                role = ApiConstant.PATIENT;
            }
        }
        //用户角色
        role = (int) SPUtils.getSP(this,AppConstants.KEY_ROLE, ApiConstant.PATIENT);

        fragmentManager = getSupportFragmentManager();
    }
    /**
     * 切换fragment
     */
    @CheckLogin
    private void selectFragment(int type) {
        index = type;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (type){
            case AppConstants.NEWS_FRAGMENT:
                if (newsFragment == null) {
                    newsFragment = NewsFragment.newInstance(AppConstants.NEWS_FRAGMENT);
                    fragmentTransaction.add(R.id.contentContainer,newsFragment,FRAGMENT_TAGS[0]);
                }else{
                    fragmentTransaction.show(newsFragment);
                }
                break;
            case AppConstants.APPOINTMENT_FRAGMENT:
                if (hospitalFragment == null) {
                    hospitalFragment = HospitalFragment.newInstance(AppConstants.APPOINTMENT_FRAGMENT);
                    fragmentTransaction.add(R.id.contentContainer, hospitalFragment,FRAGMENT_TAGS[1]);
                }else{
                    fragmentTransaction.show(hospitalFragment);
                }
                break;
            case AppConstants.APPOINTMENT_MANAGER_FRAGMENT:
                if(role==ApiConstant.PATIENT){
                    if (appointmentFragmentPatient == null) {
                        appointmentFragmentPatient = AppointmentManagerFragmentPatient.newInstance(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,appointmentFragmentPatient,FRAGMENT_TAGS[2]);
                    }else{
                        fragmentTransaction.show(appointmentFragmentPatient);
                    }
                }else if(role == ApiConstant.DOCTOR){
                    if (appointmentFragmentDoctor == null) {
                        appointmentFragmentDoctor = AppointmentManagerFragmentDoctor.newInstance(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,appointmentFragmentDoctor,FRAGMENT_TAGS[2]);
                    }else{
                        fragmentTransaction.show(appointmentFragmentDoctor);
                    }
                }else{
                    if (appointmentFragmentPatient == null) {
                        appointmentFragmentPatient = AppointmentManagerFragmentPatient.newInstance(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,appointmentFragmentPatient,FRAGMENT_TAGS[2]);
                    }else{
                        fragmentTransaction.show(appointmentFragmentPatient);
                    }
                }
                break;
            case AppConstants.PERSONAL_FRAGMENT:
                if(role==ApiConstant.PATIENT){
                    if (personalFragmentPatient == null) {
                        personalFragmentPatient = PersonalFragmentPatient.newInstance(AppConstants.PERSONAL_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,personalFragmentPatient,FRAGMENT_TAGS[3]);
                    }else{
                        fragmentTransaction.show(personalFragmentPatient);
                    }
                }else if(role == ApiConstant.DOCTOR){
                    if (personalFragmentDoctor == null) {
                        personalFragmentDoctor = PersonalFragmentDoctor.newInstance(AppConstants.PERSONAL_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,personalFragmentDoctor,FRAGMENT_TAGS[3]);
                    }else{
                        fragmentTransaction.show(personalFragmentDoctor);
                    }
                }else{
                    if (personalFragmentPatient == null) {
                        personalFragmentPatient = PersonalFragmentPatient.newInstance(AppConstants.PERSONAL_FRAGMENT);
                        fragmentTransaction.add(R.id.contentContainer,personalFragmentPatient,FRAGMENT_TAGS[3]);
                    }else{
                        fragmentTransaction.show(personalFragmentPatient);
                    }
                }
                break;
            default:
                break;
        }
        setToolBarTitle(titles[type]);
        bottomBar.selectTabAtPosition(type);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有fragment
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (newsFragment != null) {
            fragmentTransaction.hide(newsFragment);
        }

        if (hospitalFragment != null) {
            fragmentTransaction.hide(hospitalFragment);
        }

        if (appointmentFragmentDoctor != null) {
            fragmentTransaction.hide(appointmentFragmentDoctor);
        }
        if (personalFragmentDoctor != null) {
            fragmentTransaction.hide(personalFragmentDoctor);
        }

        if (appointmentFragmentPatient != null) {
            fragmentTransaction.hide(appointmentFragmentPatient);
        }
        if (personalFragmentPatient != null) {
            fragmentTransaction.hide(personalFragmentPatient);
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBarNoBack(titles[0]);
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(FRAGMENT_TAG_KEY);
            newsFragment = (NewsFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[0]);
            switch (role){//根据不同角色加载不同界面
                case ApiConstant.DOCTOR:
                    appointmentFragmentDoctor = (AppointmentManagerFragmentDoctor) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[1]);
                    personalFragmentDoctor = (PersonalFragmentDoctor) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[2]);
                    break;
                case ApiConstant.PATIENT:
                    appointmentFragmentPatient = (AppointmentManagerFragmentPatient) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[1]);
                    personalFragmentPatient = (PersonalFragmentPatient) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[2]);
                    break;
            }
        }
        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                switch (newTabId){
                    case R.id.tab_news:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,false);
                        //切换订单类型，切换fragment
                        selectFragment(AppConstants.NEWS_FRAGMENT);
                        return true;
                    case R.id.tab_appoint:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,false);
                        //切换订单类型，切换fragment
                        selectFragment(AppConstants.APPOINTMENT_FRAGMENT);
                        return true;
                    case R.id.tab_appoint_manager:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,true);
                        //切换订单类型，切换fragment
                        selectFragment(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
                        return true;
                    case R.id.tab_personal:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,true);
                        //切换订单类型，切换fragment
                        selectFragment(AppConstants.PERSONAL_FRAGMENT);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
        //默认进入新闻界面
        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,false);
        selectFragment(AppConstants.NEWS_FRAGMENT);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }


    //保存存储状态
    @Override
    protected void saveInstanceState(Bundle outState) {
        outState.putInt(FRAGMENT_TAG_KEY,index);
        super.saveInstanceState(outState);
    }
}
