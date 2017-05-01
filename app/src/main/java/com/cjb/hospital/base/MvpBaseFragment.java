package com.cjb.hospital.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by cjb
 */
public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseFragment{
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建presenter
     * @return
     */
    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        //防止内存泄漏,取消与view的关联
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }



}
