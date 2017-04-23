package com.zyl.hospital.registration.ui.mvp.appoint.department;

import com.zyl.hospital.registration.base.BaseModel;
import com.zyl.hospital.registration.base.BasePresenter;
import com.zyl.hospital.registration.base.BaseView;
import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.ResultEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by cjb
 */
public interface DeptContract {
    abstract class DeptPresenter extends BasePresenter<BaseView>{
        abstract void getDeptList(String hospitalId,int page,int size);
    }

    interface DeptView extends BaseView{
        void getDeptListSucc(List<Department> hospitals);
        void getDeptListError(String msg);
    }

    interface DeptModel extends BaseModel{
        Observable<ResultEntity<List<Department>>> getDeptList(String hospitalId,int page, int size);
    }
}
