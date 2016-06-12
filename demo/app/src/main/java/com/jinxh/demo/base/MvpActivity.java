package com.jinxh.demo.base;

import android.os.Bundle;

import com.jinxh.demo.mvp.MvpView;

/**
 * Created by jinxh on 16/1/4.
 * QQ:123489504
 */
public abstract class MvpActivity<P extends BasePresent> extends BaseActivity implements MvpView {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView(false);
        }
    }
}
