package com.jinxh.demo.base;

import com.jinxh.demo.mvp.MvpBasePresenter;
import com.jinxh.demo.mvp.MvpPresenter;
import com.jinxh.demo.mvp.MvpView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jinxh on 16/1/28.
 */
public class BasePresent<V extends MvpView> extends MvpBasePresenter<V> {
    protected CompositeSubscription mCompositeSubscription;

    protected void addIOSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
