package com.timi.taalake.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzk.mvplibrary.base.BaseActivity;
import com.timi.utilslibrary.density.Density;

import butterknife.ButterKnife;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-09 14:21
 */
public abstract class BaseImpNoMvpActivity extends BaseActivity {
    private static BaseImpNoMvpActivity currentActivity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 设置以宽度/高度适配
         */
        setOrientation();
        /**
         * 调用父类的方法
         */
        super.onCreate(savedInstanceState);
        /**
         * 存储实例
         */
        currentActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismisProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    public void setOrientation() {
        Density.setDefault(this);
    }
}
