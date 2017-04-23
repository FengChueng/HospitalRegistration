package com.zyl.hospital.registration.ui.mvp.appoint.hospital;

import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.rx.RxSubscriber;

import java.util.List;

/**
 * Created by cjb
 */
public class HospitalPresenterImpl extends HospitalContract.HospitalPresenter{
    HospitalContract.HospitalView view;
    HospitalContract.HospitalModel model;

    public HospitalPresenterImpl(HospitalContract.HospitalView view){
        this.view = view;
        model = new HospitalModelImpl();
    }

    @Override
    void getHospitalList(int page, int size) {
        addSubscription(model.getHospitalList(page,size).subscribe(new RxSubscriber<ResultEntity<List<Hospital>>>(view.getActivity()) {
            @Override
            protected void _onNext(ResultEntity<List<Hospital>> listResultEntity) {
                int status = listResultEntity.getStatus();
                if(status == ApiConstant.SUCCESS){
                    view.getHospitalSucc(listResultEntity.getData());
                }else{
                    view.getHospitalError(listResultEntity.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                view.getHospitalError(message);
            }
        }));
    }
}
