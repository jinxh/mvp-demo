package com.jinxh.demo.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by jinxh on 16/1/4.
 */
public abstract class MvpFragment<P extends BasePresent> extends BaseFragment implements MvpView {
    protected P presenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView(getRetainInstance());
        }
    }

    protected abstract P createPresenter();
}
