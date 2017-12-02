package com.trj.mvvmdemo.di;

import com.trj.mvvmdemo.App;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
@Subcomponent
public interface SubComponent extends AndroidInjector<App> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
