package com.cjb.hospital.ui.mvp.user.login.next;

import android.text.TextUtils;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.rx.RxSubscriber;
import com.cjb.hospital.bean.PatientBean;

public class CompleteInfoPresenterImpl extends CompleteInfoContract.CompleteInfoPresenter {
    private CompleteInfoContract.CompleteInfoModel model;
    private CompleteInfoContract.CompleteInfoView view;

    public CompleteInfoPresenterImpl(CompleteInfoContract.CompleteInfoView view) {
        this.view = view;
        this.model = new CompleteInfoModelImpl();
    }

    private boolean checkInput(String mobilePhone, String name, int sex, long birthday) {
        if (TextUtils.isEmpty(mobilePhone)) {
            view.completeinfoError("APP发生异常,userId为空");
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            view.completeinfoError("姓名不可以为空");
            return false;
        }
        if (sex == 0) {
            view.completeinfoError("请选择性别");
            return false;
        }

        if(birthday == 0){
            view.completeinfoError("请选择生日");
            return false;
        }

        return true;
    }

    @Override
    void completeinfo(String account,String name, final int sex, long birthday) {
        //PatientBean patientBean = model.getFromLocal(view.getActivity());
//        String userId = patientBean.getMobilePhone();
        if (checkInput(account,name,sex,birthday)){
            addSubscription(model
                    .completeinfo(account,name,sex,birthday)
                    .subscribe(new RxSubscriber<ResponseEntity<PatientBean>>(view.getActivity()) {
                        @Override
                        protected void _onNext(ResponseEntity<PatientBean> t) {
                            int status = t.getStatus();
                            if(status == ApiConstant.SUCCESS){
                                view.completeinfoSucc(t.getData());
                            }else{
                                view.completeinfoError(t.getMsg());
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            view.completeinfoError(message);
                        }
                    }));
        }
    }
}
