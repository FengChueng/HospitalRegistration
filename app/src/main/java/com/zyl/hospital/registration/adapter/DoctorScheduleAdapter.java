package com.zyl.hospital.registration.adapter;

import android.content.Context;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haozhang.lib.SlantedTextView;
import com.zyl.hospital.registration.R;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.DoctorSchedule;
import com.zyl.hospital.registration.constants.ApiConstant;
import com.zyl.hospital.registration.utils.ResourceUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by cjb
 */
public class DoctorScheduleAdapter extends BaseQuickAdapter<DoctorSchedule,BaseViewHolder>{
    private String deptName;
    private DoctorBean doctorBean;
    private Context mContext;
    public DoctorScheduleAdapter(Context context, List<DoctorSchedule> data,DoctorBean doctorBean,String deptName) {
        super(R.layout.item_doctor_schedule, data);
        this.mContext = context;
        this.doctorBean = doctorBean;
        this.deptName = deptName;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DoctorSchedule item) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String scheduleDate = sdf.format(item.getScheduleDate());
        baseViewHolder.setText(R.id.schedule_date,scheduleDate)
                      .setText(R.id.dept_name,deptName);
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
