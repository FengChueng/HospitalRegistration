package com.cjb.hospital.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cjb.hospital.ui.mvp.manage.PatientManagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyl on 2016/12/23.
 */
public class ManagerFragmentPagerAdapter extends FragmentStatePagerAdapter{
    private List<PatientManagerFragment> pagerList;

    private String[] pagerTitles = {"待就诊","就诊中","已就诊"};
    public ManagerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        pagerList = new ArrayList<>();
    }

    @Override
    public PatientManagerFragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((PatientManagerFragment) obj).getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PatientManagerFragment fragment = ((PatientManagerFragment) object);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitles[position];
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setPagerList(List<PatientManagerFragment> pagerList){
        if (pagerList != null) {
            this.pagerList = pagerList;
            notifyDataSetChanged();//刷新适配器
        }
    }
}

