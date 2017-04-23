package com.zyl.hospital.registration.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity{
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 创建presenter
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        //防止内存泄漏,取消与view的关联
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
