package com.zyl.hospital.registration.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.hospital.registration.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * description:弹窗浮动加载进度条
 * Created by qyh on 2016/12/7.
 */
public class LoadingDialog {
    /** 加载数据对话框 */
    private static Dialog mLoadingDialog;

    private static SweetAlertDialog mSweetAlertDialog;
    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static void showDialogForLoading(Activity context, String msg, boolean cancelable) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText(msg);

        mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
    }

    public static void showDialogForLoading(Activity context) {
        showDialogForLoading(context,"加载中...",true);
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public static void loadSweetDialog(Context mContext,String msg){
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(R.color.dialog_loading_color);
        mSweetAlertDialog.setTitleText(msg);
        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.show();
    }

    public static void loadSweetDialog(Context mContext){
        loadSweetDialog(mContext,"加载中...");
    }

    public static void cancleSweetDialog(){
        if (mSweetAlertDialog != null&&mSweetAlertDialog.isShowing()) {
            mSweetAlertDialog.cancel();
        }
    }

    public static void dismissSweetDialog(){
        if (mSweetAlertDialog != null&&mSweetAlertDialog.isShowing()) {
            mSweetAlertDialog.dismiss();
        }
    }

}
