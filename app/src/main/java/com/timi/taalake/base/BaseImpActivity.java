package com.timi.taalake.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzk.mvplibrary.base.BaseActivity;
import com.jzk.mvplibrary.presenter.MvpPresenter;
import com.jzk.mvplibrary.view.MvpView;
import com.timi.utilslibrary.density.Density;

import butterknife.ButterKnife;

/**
 * @author jzk
 * create at: 2018/7/30 11:34
 */
public abstract class BaseImpActivity<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity {

    private P presenter;
    private V view;
    private static BaseImpActivity currentActivity = null;

    public P getPresenter() {
        return presenter;
    }

    public V getView() {
        return view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Density.setDefault(this);
        /**
         * 绑定 presenter
         */
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        /**
         * view
         */
        if (this.view == null) {
            this.view = createView();
        }
        /**
         * attach
         */
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }

        /**
         * 调用父类的方法
         */
        super.onCreate(savedInstanceState);
        /**
         * 存储实例
         */
        currentActivity = this;
    }

    /**
     * 绑定P层
     *
     * @return
     */
    public abstract P createPresenter();

    /**
     * 创建View层
     *
     * @return
     */
    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
        dismisProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //current activity  为了解决progress  dialog 弹出 context改变的问题
        currentActivity = this;
    }
    /**
     * 获取当前Activity的实例
     *
     * @return
     */
    public static Activity getCurrentActivty() {
        return currentActivity;
    }
    /**
     * 如果项目中用到 butterknife则可以在方法中直接返回绑定的方法
     * 否则在initView中调用View的方法时会报错
     */
    @Override
    protected void initButterKnife() {
        super.initButterKnife();
        ButterKnife.bind(this);
    }
}
