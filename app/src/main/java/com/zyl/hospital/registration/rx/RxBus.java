package com.zyl.hospital.registration.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxBus
 * Created by zhangyinglong on 2017/4/25.
 * <p/>
 * 使用:
 * 1:接受事件
 *  // rxSubscription是一个Subscription的全局变量，这段代码可以在onCreate/onStart等生命周期内
 *  rxSubscription = RxBus.getDefault().toObserverable(UserEvent.class)
 *                  .subscribe(new Action1<UserEvent>() {
 *                      @Override
 *                      public void call(UserEvent userEvent) {
 *                          long id = userEvent.getId();
 *                          String name = userEvent.getName();
 *                             ...
 *                      }
 *                  },new Action1<Throwable>() {
 *                      @Override
 *                      public void call(Throwable throwable) {
 *                           // TODO: 处理异常
                        }
                    });
 * 2.发送事件:
 *  RxBus.getDefault().post(new UserEvent (1, "yoyo"));
 *
 * 3.取消订阅：
 * @Override
 *  protected void onDestroy() {
 *      super.onDestroy();
 *      if(!rxSubscription.isUnsubscribed()) {
 *          rxSubscription.unsubscribe();
 *      }
 *  }
 */


public class RxBus {
    private static volatile RxBus defaultInstance;

    private final Subject<Object, Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // 发送一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        // ofType = filter + cast
        return bus.ofType(eventType);
    }
}