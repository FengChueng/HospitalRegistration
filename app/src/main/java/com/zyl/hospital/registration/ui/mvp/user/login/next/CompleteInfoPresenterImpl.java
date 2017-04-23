package com.zyl.hospital.registration.ui.mvp.user.login.next;

import android.text.TextUtils;

import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.rx.RxSubscriber;

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
    void completeinfo(String name, final int sex, long birthday) {
        PatientBean patientBean = model.getFromLocal(view.getActivity());
        String userId = patientBean.getMobilePhone();
        if (checkInput(userId,name,sex,birthday)){
            addSubscription(model
                    .completeinfo(userId,name,sex,birthday)
                    .subscribe(new RxSubscriber<ResultEntity<PatientBean>>(view.getActivity()) {
                        @Override
                        protected void _onNext(ResultEntity<PatientBean> t) {
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
