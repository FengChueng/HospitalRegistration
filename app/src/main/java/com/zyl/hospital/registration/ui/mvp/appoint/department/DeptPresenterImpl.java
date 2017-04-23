package com.zyl.hospital.registration.ui.mvp.appoint.department;

import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.rx.RxSubscriber;

import java.util.List;

/**
 * Created by cjb
 */
public class DeptPresenterImpl extends DeptContract.DeptPresenter {
    DeptContract.DeptView view;
    DeptContract.DeptModel model;

    public DeptPresenterImpl(DeptContract.DeptView view){
        this.view = view;
        model = new DeptModelImpl();
    }

    @Override
    void getDeptList(String hositalId,int page, int size) {
        addSubscription(model.getDeptList(hositalId,page,size).subscribe(new RxSubscriber<ResultEntity<List<Department>>>(view.getActivity()) {
            @Override
            protected void _onNext(ResultEntity<List<Department>> listResultEntity) {
                int status = listResultEntity.getStatus();
                if(status == ApiConstant.SUCCESS){
                    view.getDeptListSucc(listResultEntity.getData());
                }else{
                    view.getDeptListError(listResultEntity.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                view.getDeptListError(message);
            }
        }));
    }
}
