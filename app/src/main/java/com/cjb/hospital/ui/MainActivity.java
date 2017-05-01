package com.cjb.hospital.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.annotation.aspect.CheckLogin;
import com.cjb.hospital.base.BaseActivity;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.ui.mvp.appoint.hospital.HospitalFragment;
import com.cjb.hospital.ui.mvp.user.userinfo.UserInfoFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.TabSelectionInterceptor;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.bean.BaseBean;
import com.cjb.hospital.bean.PatientBean;
import com.cjb.hospital.ui.fragment.PersonalFragmentDoctor;
import com.cjb.hospital.ui.mvp.manage.MainManagerFragment;
import com.cjb.hospital.utils.SPUtils;

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
    //预约界面
    private HospitalFragment hospitalFragment;
    //预约管理界面
    private MainManagerFragment appointmentFragmentPatient;
    //医生界面Fragment
    private PersonalFragmentDoctor personalFragmentDoctor;
    //病人界面Fragment
    private UserInfoFragment userInfoFragment;
    /* fragment索引键名 */
    private static final String FRAGMENT_TAG_KEY = "tag_name";
    /* 当前显示的fragment索引 */
    private int index = 1;
    //用户当前角色
    private int role;

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
            case AppConstants.APPOINTMENT_FRAGMENT:
                if (hospitalFragment == null) {
                    hospitalFragment = HospitalFragment.newInstance(AppConstants.APPOINTMENT_FRAGMENT);
                    fragmentTransaction.add(R.id.contentContainer, hospitalFragment,FRAGMENT_TAGS[1]);
                }else{
                    fragmentTransaction.show(hospitalFragment);
                }
                break;
            case AppConstants.APPOINTMENT_MANAGER_FRAGMENT:
                if(appointmentFragmentPatient == null){
                    if(role == ApiConstant.DOCTOR){
                        appointmentFragmentPatient = MainManagerFragment.newInstance(ApiConstant.DOCTOR);
                    }else{
                        appointmentFragmentPatient = MainManagerFragment.newInstance(ApiConstant.PATIENT);
                    }
                    fragmentTransaction.add(R.id.contentContainer,appointmentFragmentPatient,FRAGMENT_TAGS[2]);
                }else{
                    fragmentTransaction.show(appointmentFragmentPatient);
                }
//                if(role == ApiConstant.DOCTOR){
//                    if (appointmentFragmentPatient == null) {
//                        appointmentFragmentPatient = MainManagerFragment.newInstance(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
//                        fragmentTransaction.add(R.id.contentContainer,appointmentFragmentPatient,FRAGMENT_TAGS[2]);
//                    }else{
//                        fragmentTransaction.show(appointmentFragmentPatient);
//                    }
//                }else{
//                    if (appointmentFragmentDoctor == null) {
//                        appointmentFragmentDoctor = DoctorManagerFragmentDoctor.newInstance(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
//                        fragmentTransaction.add(R.id.contentContainer,appointmentFragmentDoctor,FRAGMENT_TAGS[2]);
//                    }else{
//                        fragmentTransaction.show(appointmentFragmentDoctor);
//                    }
//                }
                break;
            case AppConstants.PERSONAL_FRAGMENT:
                if(userInfoFragment == null){
                    if(role == ApiConstant.DOCTOR){
                        userInfoFragment = UserInfoFragment.newInstance((ApiConstant.DOCTOR));
                    }else{
                        userInfoFragment = UserInfoFragment.newInstance(ApiConstant.PATIENT);
                    }
                    fragmentTransaction.add(R.id.contentContainer, userInfoFragment,FRAGMENT_TAGS[3]);
                }else{
                    fragmentTransaction.show(userInfoFragment);
                }
                break;
            default:
                break;
        }
        setToolBarTitle(titles[type]);
        bottomBar.selectTabAtPosition(type-1);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有fragment
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {

        if (hospitalFragment != null) {
            fragmentTransaction.hide(hospitalFragment);
        }

        if (personalFragmentDoctor != null) {
            fragmentTransaction.hide(personalFragmentDoctor);
        }

        if (appointmentFragmentPatient != null) {
            fragmentTransaction.hide(appointmentFragmentPatient);
        }
        if (userInfoFragment != null) {
            fragmentTransaction.hide(userInfoFragment);
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBarNoBack(titles[0]);
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(FRAGMENT_TAG_KEY);
            hospitalFragment = (HospitalFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[1]);
            appointmentFragmentPatient = (MainManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[2]);
            switch (role){//根据不同角色加载不同界面
                case ApiConstant.DOCTOR:
                    personalFragmentDoctor = (PersonalFragmentDoctor) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[3]);
                    break;
                case ApiConstant.PATIENT:
                    userInfoFragment = (UserInfoFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAGS[3]);
                    break;
            }
        }
        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                switch (newTabId){
                    case R.id.tab_appoint:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,false);
                        //切换fragment
                        selectFragment(AppConstants.APPOINTMENT_FRAGMENT);
                        return true;
                    case R.id.tab_appoint_manager:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,true);
                        //切换fragment
                        selectFragment(AppConstants.APPOINTMENT_MANAGER_FRAGMENT);
                        return true;
                    case R.id.tab_personal:
                        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,true);
                        //切换fragment
                        selectFragment(AppConstants.PERSONAL_FRAGMENT);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
        //默认进入预约挂号界面
        SPUtils.putSP(MainActivity.this,AppConstants.KEY_IS_NEED_LOGIN,false);
        selectFragment(AppConstants.APPOINTMENT_FRAGMENT);
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
