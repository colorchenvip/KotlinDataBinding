package com.colorchen.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
            Logger.addLogAdapter(new AndroidLogAdapter());
            Stetho.initializeWithDefaults(this);
        }
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        ARouter.init(this);
    }
}
