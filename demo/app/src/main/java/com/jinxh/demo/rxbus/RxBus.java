package com.jinxh.demo.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 利用RxJava代替eventBus
 * <p/>
 * Created by jinxh on 15/12/31.
 */

public class RxBus {
    private static RxBus mRxBus = null;
    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     */

    private Subject<Object, Object> mRxBusObservable = new SerializedSubject<>(PublishSubject.create());

    public static synchronized RxBus getInstance() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
        }
        return mRxBus;
    }

    public void post(Object o) {
        if (hasObservers()) {
            mRxBusObservable.onNext(o);
        }
    }

    public Observable<Object> getObservable() {
        return mRxBusObservable;
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mRxBusObservable.hasObservers();
    }
}
