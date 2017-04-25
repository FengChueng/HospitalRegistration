package com.zyl.hospital.registration.ui.mvp.appoint.schedule;

import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.rx.RxSubscribers;

/**
 * Created by zhangyinglong on 2017/4/25.
 */
public class DoctorSchedulePresenterImpl extends DoctorScheduleContract.DoctorSchedulePresenter {
    DoctorScheduleContract.DoctorScheduleView view;
    DoctorScheduleContract.DoctorScheduleModel modle;
    public DoctorSchedulePresenterImpl(DoctorScheduleContract.DoctorScheduleView view){
        this.view = view;
        this.modle = new DoctorScheduleModelImpl();
    }

    @Override
    void getDoctorSchedule(String doctorId, int page, int size) {
        addSubscription(modle.getDoctorSchedule(doctorId,page,size).subscribe(new RxSubscribers<DoctorBean>(view.getActivity()) {
            @Override
            protected void _onNext(DoctorBean doctorBean) {

            }

            @Override
            protected void _onError(String message) {

            }

            @Override
            protected void _noData(String msg) {

            }
        }));
    }
}
