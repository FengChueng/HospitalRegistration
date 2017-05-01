package com.cjb.hospital.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zyl.hospital.hospital.R;
import com.cjb.hospital.widget.slideback.SlideBackActivity;

import butterknife.ButterKnife;

/**
 * 支持滑动左侧退出Activity
 * extends SlideBackActivity
 * setSlideable(true);//允许滑动左侧退出activity(默认)
 *
 * 支持自动适配
 * SlideBackActivity extends AutoLayoutActivity
 */
public abstract class BaseActivity extends SlideBackActivity {
    /* 日志标志 */
    protected final String TAG = this.getClass().getSimpleName();
    //private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");
        //由前一个activity传递的值
        Bundle bundle = getIntent().getExtras();
        initParams(bundle);

        // 设置布局
        setContentView(getStatusLayoutView());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        restoreInstanceState(savedInstanceState);
        setSlideable(true);
        setPreviousActivitySlideFollow(true);

    }

    /**
     * 获取多状态布局 View
     */
    protected View getStatusLayoutView() {
        View contentView = null;
//        mStatusLayout = (StatusLayout) LayoutInflater.from(this).inflate(R.layout.layout_status_view, null);
        if (getLayoutID() != 0) {
            contentView = LayoutInflater.from(this).inflate(getLayoutID(), null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT);
//            mStatusLayout.addView(contentView, params);
            contentView.setLayoutParams(params);
        }
        return contentView;
    }


    /**
     * 恢复之前状态(onCreate()方法中恢复)
     * @param savedInstanceState
     */
    protected void restoreInstanceState(Bundle savedInstanceState){
        // TODO: if(savedInstanceState!=null){
        // TODO:    savedInstanceState.getInt("STORE_STATE",mCurrentState);}
    }

    /**
     * 恢复之前状态(onRestoreInstanceState中恢复)
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //// TODO: savedInstanceState.getInt("STORE_STATE",mCurrentState);
    }

    /**
     * 状态保存
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    /**
     * 保存用户当前状态
     *
     * @param outState
     */
    protected void saveInstanceState(Bundle outState){
        // TODO: outState.putInt(STORE_STATE, mCurrentState);
    }


    /**
     * 初始化参数
     *
     * @param params 传递参数
     */
    protected abstract void initParams(Bundle params);

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 得到布局id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutID();


    /**
     * 沉浸状态栏
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化toolbar
     * @param title 标题
     * @return
     */
    public Toolbar initToolBar(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBarTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }



    /**
     * 设置toolbar标题
     * @param title     标题
     */
    public void setToolBarTitle(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
    }

    /**
     * 没有返回按钮
     * @param title
     * @return
     */
    public Toolbar initToolBarNoBack(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
