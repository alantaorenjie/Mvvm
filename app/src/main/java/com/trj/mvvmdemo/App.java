package com.trj.mvvmdemo;


import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.trj.mvvmdemo.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
public class App extends DaggerApplication{


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}

