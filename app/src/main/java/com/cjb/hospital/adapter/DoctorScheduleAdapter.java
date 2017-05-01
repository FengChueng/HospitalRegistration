package com.cjb.hospital.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cjb.hospital.bean.DoctorSchedule;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.constants.AppConstants;
import com.cjb.hospital.utils.ResourceUtils;
import com.cjb.hospital.utils.SPUtils;
import com.haozhang.lib.SlantedTextView;
import com.zyl.hospital.hospital.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by cjb
 */
public class DoctorScheduleAdapter extends BaseQuickAdapter<DoctorSchedule,BaseViewHolder>{
    private Context mContext;
    public DoctorScheduleAdapter(Context context, List<DoctorSchedule> data) {
        super(R.layout.item_doctor_schedule, data);
        this.mContext = context;
        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.nodata_layout,null);
        setEmptyView(emptyView);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DoctorSchedule item) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String deptName = (String) SPUtils.getSP(mContext, AppConstants.KEY_DEPT_NAME,"");


        String scheduleDate = sdf.format(item.getScheduleDate());
        baseViewHolder.setText(R.id.schedule_date,scheduleDate)
                      .setText(R.id.dept_name,deptName);
//                      .addOnClickListener(R.id.appointment_btn);
        Button button = baseViewHolder.getView(R.id.appointment_btn);
        SlantedTextView slantedTextView = baseViewHolder.getView(R.id.slv_right);
        if (item.getStatus()== ApiConstant.DOCTOR_SCHEDULE_POSSIBLE) {
            button.setEnabled(true);
            slantedTextView.setText("可预约");
        }else if(item.getStatus() == ApiConstant.DOCTOR_SCHEDULE_FULL){
            button.setEnabled(false);
            slantedTextView.setText("预约已满");
            slantedTextView.setBackgroundColor(ResourceUtils.getColor(R.color.bsp_red));
        }else if(item.getStatus() == ApiConstant.DOCTOR_SCHEDULE_REST){
            button.setEnabled(false);
            slantedTextView.setText("休息");
            slantedTextView.setBackgroundColor(ResourceUtils.getColor(R.color.bsp_red));
        }




    }
}
