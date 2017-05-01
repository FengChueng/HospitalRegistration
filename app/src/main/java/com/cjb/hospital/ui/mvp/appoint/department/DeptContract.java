package com.cjb.hospital.ui.mvp.appoint.department;

import com.cjb.hospital.base.BaseModel;
import com.cjb.hospital.base.BasePresenter;
import com.cjb.hospital.base.BaseView;
import com.cjb.hospital.bean.Department;
import com.cjb.hospital.bean.ResponseEntity;

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
        void getDeptListSucc(List<Department> departments);
        void getDeptListError(String msg);
    }

    interface DeptModel extends BaseModel{
        Observable<ResponseEntity<List<Department>>> getDeptList(String hospitalId, int page, int size);
    }
}
