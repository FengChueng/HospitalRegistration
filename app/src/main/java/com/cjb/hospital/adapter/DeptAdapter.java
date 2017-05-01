package com.cjb.hospital.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.bean.Department;

import java.util.List;

/**
 * Created by cjb
 */

public class DeptAdapter extends BaseQuickAdapter<Department,BaseViewHolder> {
    public DeptAdapter(Context context, List<Department> hospitals) {
        super(R.layout.item_dept, hospitals);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Department item) {
        helper.setText(R.id.dept_name, item.getDeptName());
    }
}
