package com.wd.logindemo2.core;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;

/**
 * @author dingtao
 * @date 2019/4/28 16:28
 * qq:1940870847
 */
public class DTApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(getApplicationContext(), "5cc562cc0cafb2a6b2000e8a", "小米", UMConfigure.DEVICE_TYPE_PHONE, "");
    }
}
