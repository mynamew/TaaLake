package com.timi.taalake.base;

import android.app.Application;
import android.content.res.Configuration;


import com.jzk.mvplibrary.utils.LanguageUtil;
import com.timi.utilslibrary.density.Density;

import java.util.Locale;

/**
 * @author timi
 * @date 2019/8/9
 * @time 14:22
 * @desc base app
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置语言
        languageSwitch();
        //设置屏幕适配
        Density.setDensity(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        languageSwitch();
    }

    private void languageSwitch() {
        //自己写的工具包（如下）
        Locale locale = LanguageUtil.getLocale(this);
        LanguageUtil.updateLocale(this, locale);
    }
}
