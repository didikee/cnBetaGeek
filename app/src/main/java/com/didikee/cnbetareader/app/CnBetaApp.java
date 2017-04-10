package com.didikee.cnbetareader.app;

import android.app.Application;

import com.aitangba.swipeback.ActivityLifecycleHelper;

/**
 * Created by didik on 2017/4/10.
 */

public class CnBetaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());
    }
}
