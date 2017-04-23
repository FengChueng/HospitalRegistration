package com.zyl.hospital.registration.ui.mvp.user.login.next;

import android.content.Context;

import com.google.gson.Gson;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.AppConfig;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;
import com.zyl.hospital.registration.utils.SPUtils;

import rx.Observable;


public class CompleteInfoModelImpl implements CompleteInfoContract.CompleteInfoModel{

    @Override
    public Observable<ResultEntity<PatientBean>> completeinfo(String account,String name, int sex, long birthday) {
        if(AppConfig.useMock){
            return null;
        }
        return Api
                .getInstance()
                .createService(ApiService.class)
                .modifyPatientInfo(account,null,null,name,sex,birthday,"",account)
                .compose(RxSchedulers.<ResultEntity<PatientBean>>io_main());
    }

    @Override
    public PatientBean getFromLocal(Context context) {
        PatientBean patientBean = new Gson().fromJson(SPUtils.getSP(context,"KEY_USER_JSON",null).toString(), PatientBean.class);
        return patientBean;
    }
}
