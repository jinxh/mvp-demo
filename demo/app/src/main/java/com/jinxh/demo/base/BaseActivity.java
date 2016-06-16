package com.jinxh.demo.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jinxh.demo.AppContext;
import com.jinxh.demo.R;
import com.jinxh.demo.widget.LoadingDialog;
import com.jakewharton.rxbinding.view.RxView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jinxh on 16/1/4.
 * QQ:123489504
 */
public abstract class BaseActivity extends AppCompatActivity{
    private SystemBarTintManager mTintManager;
    private LoadingDialog mLoadingDialog;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (isHiddenStatusBar()) {
            initBarTint();
            initTopBar();
        }
        initData();
        initEvent();
        initView();
    }

    protected boolean isHiddenStatusBar() {
        return AppContext.HIDDEN_STATUS_BAR;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void backButtonPressed(View view) {
        finish();
    }

    protected abstract int getLayoutId();

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    protected void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
        // 快速点击
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    protected void setOnClickThrottleFirst(View view) {
        // 快速点击
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void initBarTint() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // 全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                setTranslucentStatus(window);
            }
        }
        setTintResource(getTintResourceId());
    }

    private void initTopBar() {
        // 4.4以上设置头部导航高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View topBar = findViewById(R.id.activity_top_bar);
            if (topBar == null) {
                return;
            }
            SystemBarTintManager.SystemBarConfig config = new SystemBarTintManager(this).getConfig();
            int statusBarHeight = config.getStatusBarHeight();
            topBar.setPadding(0, statusBarHeight, 0, 0);

            int height = topBar.getLayoutParams().height;
            topBar.getLayoutParams().height = statusBarHeight + height;
        }
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @TargetApi(19)
    private void setTranslucentStatus(Window window) {
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        window.setAttributes(winParams);
    }

    public void showMessage(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void setTintResource(int res) {
        mTintManager.setTintResource(res);
    }

    protected int getTintResourceId() {
        return R.color.transparent;
    }
}
