package com.trj.mvvmdemo.ui.base;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.trj.mvvmdemo.di.AppComponent;
import com.trj.mvvmdemo.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
public class App extends Application implements HasActivityInjector {

    public static AppComponent mAppComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.create();
        mAppComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}

