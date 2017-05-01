package com.cjb.hospital.rx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangyinglong on 2017/2/28.
 */
public class RxManager {
    /*管理Observables 和 Subscribers订阅*/
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * 管理Observables 和 Subscribers
     * @param subscription
     */
    public void add(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }

    public void unsubscribe(){
        mCompositeSubscription.unsubscribe();//取消订阅
    }

}
