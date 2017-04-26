package com.zyl.hospital.registration.ui.mvp.user.login.next;

import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.http.api.Api;
import com.zyl.hospital.registration.http.api.ApiService;
import com.zyl.hospital.registration.rx.RxSchedulers;

import rx.Observable;


public class CompleteInfoModelImpl implements CompleteInfoContract.CompleteInfoModel{

    @Override
    public Observable<ResponseEntity<PatientBean>> completeinfo(String account, String name, int sex, long birthday) {
//        if(AppConfig.useMock){
//            return getFromLocal();
//        }
        return Api
                .getInstance()
                .createService(ApiService.class)
                .modifyPatientInfo(account,"","",name,Integer.valueOf(sex),Long.valueOf(birthday),"",account)
                .compose(RxSchedulers.<ResponseEntity<PatientBean>>io_main());
    }

//    @Override
//    public PatientBean getFromLocal(Context context) {
//        PatientBean patientBean = new Gson().fromJson(SPUtils.getSP(context, AppConstants.KEY_USER_JSON,null).toString(), PatientBean.class);
//        return patientBean;
//    }
}
