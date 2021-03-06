package com.cjb.hospital.rx;

import android.content.Context;

import com.cjb.hospital.utils.NetWorkUtils;
import com.cjb.hospital.utils.ResourceUtils;
import com.cjb.hospital.widget.LoadingDialog;
import com.cjb.hospital.App;
import com.zyl.hospital.hospital.R;

import rx.Subscriber;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
public abstract class RxSubscriber<T> extends Subscriber<T> {

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

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, ResourceUtils.getString(R.string.loading),false);
    }
    public RxSubscriber(Context context, boolean showDialog) {
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
    public void onNext(T t) {
        if (showDialog)
            LoadingDialog.cancleSweetDialog();
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
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

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
