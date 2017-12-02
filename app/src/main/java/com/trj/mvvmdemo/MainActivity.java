package com.trj.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.common.BindingAdapter;
import com.trj.mvvmdemo.databinding.ActivityMainBinding;
import com.trj.mvvmdemo.di.DaggerAppComponent;
import com.trj.mvvmdemo.model.GankioData;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends DaggerAppCompatActivity {

    @BindView(R.id.main_hw_tv)
    TextView mHwTv;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private ViewDataBinding binding;
    private BindingAdapter mAdapter;

    @Inject
    OkHttpClient mOkHttp;

    @Inject
    ApiService mApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BindingAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }


    @OnClick(R.id.main_hw_tv)
    public void onViewClicked() {
        mApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankioData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.i("onSubscribe:%s", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull final GankioData gankioData) {
                        Logger.i("数据：%s", gankioData.toString());

                        mAdapter.replace(gankioData.results);

                        binding.setVariable(BR.data,gankioData);

                        gankioData.results.get(0).who = "动态变化";
                        gankioData.results.get(0).who = String.format(Locale.getDefault(), "动态变化 %s", Math.random() * 10);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    for (GankioData.ResultsBean bean: gankioData.results){
                                        bean.setWho(String.format(Locale.getDefault(),"%s  %s",bean.desc,Math.random() * 10));
                                    }
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("onComplete");
                    }
                });
    }
}
