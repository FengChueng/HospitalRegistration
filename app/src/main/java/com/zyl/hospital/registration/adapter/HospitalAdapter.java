package com.zyl.hospital.registration.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haozhang.lib.SlantedTextView;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.utils.ImageLoader;
import com.zyl.hospital.registration.widget.CircleImageView;

import java.util.List;

import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_1_A;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_1_B;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_1_C;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_2_A;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_2_B;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_2_C;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_3_A;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_3_B;
import static com.zyl.hospital.registration.constants.ApiConstant.HOSPITAL_LEVEL_3_C;

/**
 * Created by cjb
 */

public class HospitalAdapter extends BaseQuickAdapter<Hospital,BaseViewHolder> {

    public HospitalAdapter(Context context, List<Hospital> hospitals) {
        super(R.layout.item_hospital, hospitals);
        this.mContext = context;
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
            case HOSPITAL_LEVEL_3_A:
                return "三甲";
            case HOSPITAL_LEVEL_3_B:
                return "三乙";
            case HOSPITAL_LEVEL_3_C:
                return "三丙";
            case HOSPITAL_LEVEL_2_A:
                return "二甲";
            case HOSPITAL_LEVEL_2_B:
                return "二乙";
            case HOSPITAL_LEVEL_2_C:
                return "二丙";
            case HOSPITAL_LEVEL_1_A:
                return "一级";
            case HOSPITAL_LEVEL_1_B:
                return "一级";
            case HOSPITAL_LEVEL_1_C:
                return "一级";
        }
        return "";
    }
}
