package com.zyl.hospital.registration.ui.mvp.appoint.doctor;

import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.rx.RxSubscriber;

import java.util.List;

/**
 * Created by cjb
 */
public class DoctorPresenterImpl extends DoctorContract.DoctorPresenter {
    DoctorContract.DoctorView view;
    DoctorContract.DoctorModel model;

    public DoctorPresenterImpl(DoctorContract.DoctorView view){
        this.view = view;
        model = new DoctorModelImpl();
    }

    @Override
    void getDoctor(String deptId, int page, int size) {
        addSubscription(model.getDeptList(deptId,page,size).subscribe(new RxSubscriber<ResultEntity<List<DoctorBean>>>(view.getActivity()) {
            @Override
            protected void _onNext(ResultEntity<List<DoctorBean>> listResultEntity) {
                int status = listResultEntity.getStatus();
                if(status == ApiConstant.SUCCESS){
                    view.getDoctorListSucc(listResultEntity.getData());
                }else{
                    view.getDoctorListError(listResultEntity.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                view.getDoctorListError(message);
            }
        }));
    }
}
