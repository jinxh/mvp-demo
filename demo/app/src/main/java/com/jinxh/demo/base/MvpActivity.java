package com.jinxh.demo.base;

import android.os.Bundle;

/**
 * Created by jinxh on 16/1/4.
 * QQ:123489504
 */
public abstract class MvpActivity<P extends BasePresent> extends BaseActivity implements MvpView {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView(false);
        }
    }
}
