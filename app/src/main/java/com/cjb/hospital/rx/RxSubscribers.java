package com.cjb.hospital.rx;

import android.content.Context;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.utils.ResourceUtils;
import com.cjb.hospital.widget.LoadingDialog;
import com.cjb.hospital.App;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.utils.NetWorkUtils;

import rx.Subscriber;

/**
 * RxJava调度管理
 * Created by qyh on2016/12/6.
 */
public abstract class RxSubscribers<T> extends Subscriber<ResponseEntity<T>>{
    private Context mContext;
    private String msg;
    private boolean showDialog=false;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscribers(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscribers(Context context) {
        this(context, ResourceUtils.getString(R.string.loading),false);
    }
    public RxSubscribers(Context context, boolean showDialog) {
        this(context, ResourceUtils.getString(R.string.loading),showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancleSweetDialog();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.loadSweetDialog(mContext,msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(ResponseEntity<T> t) {
        if (showDialog)
            LoadingDialog.cancleSweetDialog();
        if(t == null){//网络连接异常
            _onError(ResourceUtils.getString(R.string.net_error));
            return;
        }
        //有网络请求
        if (ApiConstant.SUCCESS==t.getStatus()) {//数据返回成功
            if(t.getData() != null){
                _onNext(t.getData());
            }else{
                _noData("无数据");
            }
        }else if(ApiConstant.FIAL == t.getStatus()){
            _onError(t.getMsg());
        }
    }
    @Override
    public void onError(Throwable e) {
        //无网络请求
        if (showDialog)
            LoadingDialog.cancleSweetDialog();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(App.getInstance())) {
            _onError(ResourceUtils.getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof RuntimeException) {
            _onError(e.getMessage());
        }
        //其它
        else {
            _onError(ResourceUtils.getString(R.string.net_error));
        }
    }

    /**
     * 数据返回成功,有数据
     * @param t
     */
    protected abstract void _onNext(T t);

    /**
     * 异常
     * @param message
     */
    protected abstract void _onError(String message);

    /**
     * 无数据
     * @param msg
     */
    protected abstract void _noData(String msg);
}
