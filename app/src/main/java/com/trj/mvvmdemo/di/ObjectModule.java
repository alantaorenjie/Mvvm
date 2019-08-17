package com.trj.mvvmdemo.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.config.AppConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
@Module(subcomponents = SubComponent.class)
public class ObjectModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    static ApiService provideApiService(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ApiService.class);
    }
}
