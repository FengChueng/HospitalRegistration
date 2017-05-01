package com.cjb.hospital.ui.mvp.appoint.department;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.bean.Department;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.rx.RxSubscriber;

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
        addSubscription(model.getDeptList(hositalId,page,size).subscribe(new RxSubscriber<ResponseEntity<List<Department>>>(view.getActivity()) {
            @Override
            protected void _onNext(ResponseEntity<List<Department>> listResponseEntity) {
                int status = listResponseEntity.getStatus();
                if(status == ApiConstant.SUCCESS){
                    view.getDeptListSucc(listResponseEntity.getData());
                }else{
                    view.getDeptListError(listResponseEntity.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                view.getDeptListError(message);
            }
        }));
    }
}
