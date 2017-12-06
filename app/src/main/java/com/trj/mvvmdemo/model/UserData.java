package com.trj.mvvmdemo.model;

import com.orhanobut.logger.Logger;
import com.trj.mvvmdemo.BR;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.di.DaggerAppComponent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
public class UserData {
    @Inject
    OkHttpClient mOkHttpClient;

    @Inject
    ApiService mApiService;
    
    @Inject
    public UserData() {
    }

    public void getData(){
        mApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankioData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GankioData gankioData) {
                        Logger.i("数据：%s", gankioData.toString());
                        gankioData.results.get(0).who = "动态变化";
                        gankioData.results.get(0).who = String.format(Locale.getDefault(), "动态变化 %s", Math.random() * 10);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public String toString() {
        return "UserData{" +
                "mOkHttpClient=" + mOkHttpClient +
                ", mApiService=" + mApiService +
                '}';
    }
}
