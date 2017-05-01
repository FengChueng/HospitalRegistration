package com.cjb.hospital.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.widget.CircleImageView;
import com.haozhang.lib.SlantedTextView;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.utils.ImageLoader;

import java.util.List;

/**
 * Created by cjb
 */

public class HospitalAdapter extends BaseQuickAdapter<Hospital,BaseViewHolder> {

    public HospitalAdapter(Context context, List<Hospital> hospitals) {
        super(R.layout.item_hospital, hospitals);
        this.mContext = context;
        //空布局
        View emptyView = View.inflate(context,R.layout.layout_status_empty_view,null);
        setEmptyView(emptyView);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hospital item) {
        helper.setText(R.id.hospital_name, item.getHospitalName());
        ((SlantedTextView)helper.getView(R.id.slv_right)).setText(getLevel(item.getLevel()));
        ImageLoader.getIns(mContext).load(ApiConstant.API_SERVER_URL+item.getImg(),
                (CircleImageView) helper.getView(R.id.img),R.mipmap.hospital_default,R.mipmap.hospital_default);

    }


    private String getLevel(int level){
        switch (level){
            case ApiConstant.HOSPITAL_LEVEL_3_A:
                return "三甲";
            case ApiConstant.HOSPITAL_LEVEL_3_B:
                return "三乙";
            case ApiConstant.HOSPITAL_LEVEL_3_C:
                return "三丙";
            case ApiConstant.HOSPITAL_LEVEL_2_A:
                return "二甲";
            case ApiConstant.HOSPITAL_LEVEL_2_B:
                return "二乙";
            case ApiConstant.HOSPITAL_LEVEL_2_C:
                return "二丙";
            case ApiConstant.HOSPITAL_LEVEL_1_A:
                return "一级";
            case ApiConstant.HOSPITAL_LEVEL_1_B:
                return "一级";
            case ApiConstant.HOSPITAL_LEVEL_1_C:
                return "一级";
        }
        return "";
    }
}
