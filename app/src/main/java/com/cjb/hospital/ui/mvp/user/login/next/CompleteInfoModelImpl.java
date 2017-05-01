package com.cjb.hospital.ui.mvp.user.login.next;

import com.cjb.hospital.bean.PatientBean;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.http.api.Api;
import com.cjb.hospital.http.api.ApiService;
import com.cjb.hospital.rx.RxSchedulers;

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
