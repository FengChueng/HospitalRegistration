package com.cjb.hospital.ui.mvp.appoint.hospital;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.rx.RxSubscriber;

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
        addSubscription(model.getHospitalList(page,size).subscribe(new RxSubscriber<ResponseEntity<List<Hospital>>>(view.getActivity()) {
            @Override
            protected void _onNext(ResponseEntity<List<Hospital>> listResponseEntity) {
                int status = listResponseEntity.getStatus();
                if(status == ApiConstant.SUCCESS){
                    view.getHospitalSucc(listResponseEntity.getData());
                }else{
                    view.getHospitalError(listResponseEntity.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                view.getHospitalError(message);
            }
        }));
    }
}
