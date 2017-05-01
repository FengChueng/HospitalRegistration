package com.cjb.hospital.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.bean.Appointment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by cjb
 */
public class AppointmentAdapter extends BaseQuickAdapter<Appointment,BaseViewHolder> {
    private Context mContext;
    public AppointmentAdapter(Context context,List<Appointment> data) {
        super(R.layout.item_appointment, data);
        this.mContext = context;
        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.nodata_layout,null);
        setEmptyView(emptyView);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Appointment appointment) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        baseViewHolder.setText(R.id.schedule_date,sdf.format(appointment.getClinicDate()))
                .setText(R.id.location,appointment.getLocation())
                .setText(R.id.price,appointment.getPrice()+"元");
    }
}
