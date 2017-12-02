package com.trj.mvvmdemo.di;

import com.trj.mvvmdemo.App;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ObjectModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}
