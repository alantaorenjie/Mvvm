package com.trj.mvvmdemo.di;

import com.trj.mvvmdemo.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
@Module(subcomponents = SubComponent.class)
abstract class AppModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

//    @ContributesAndroidInjector
//    abstract NoticeDialog NoticeDialog();

}
