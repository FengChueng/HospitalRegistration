package com.zyl.hospital.registration.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haozhang.lib.SlantedTextView;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.utils.ImageLoader;
import com.zyl.hospital.registration.widget.CircleImageView;

import java.util.List;

/**
 * Created by cjb
 */

public class DoctorAdapter extends BaseQuickAdapter<DoctorBean,BaseViewHolder> {

    private final String deptName;

    public DoctorAdapter(Context context, List<DoctorBean> hospitals, String deptName) {
        super(R.layout.item_doctor, hospitals);
        this.mContext = context;
        this.deptName = deptName;
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorBean item) {
        helper.setText(R.id.doctor_name,item.getRealName())
                .setText(R.id.dept_name, deptName)
                .setText(R.id.doctor_info,item.getInfo());

        int resId = 1==1?R.mipmap.appoint:R.mipmap.appoint_manager;

        ((SlantedTextView)helper.getView(R.id.slv_right)).setText(getLevel(item.getLevel()));


        ((CircleImageView)helper.getView(R.id.can_appointment)).setImageResource(resId);

        ImageLoader
                .getIns(mContext)
                .load(ApiConstant.API_SERVER_URL+item.getPortraint(),
                        (CircleImageView)helper.getView(R.id.img),
                        R.mipmap.portraint_default,R.mipmap.portraint_default);
    }

    private String getLevel(int level) {
        switch (level){
            case ApiConstant.DOCTOR_PROFESSOR:
                return "专家";
            case ApiConstant.DOCTOR_NORMAL:
                return "普通";
            default:
                return "普通";
        }
    }
}