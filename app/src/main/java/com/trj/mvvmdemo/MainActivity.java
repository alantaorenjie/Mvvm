package com.trj.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.databinding.ActivityMainBinding;
import com.trj.mvvmdemo.model.GankioData;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_hw_tv)
    TextView mHwTv;

    ApiService mApiService;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        onViewClicked();
    }

    private void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mApiService = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/Android/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttpClient)
                .build()
                .create(ApiService.class);
    }

    @OnClick(R.id.main_hw_tv)
    public void onViewClicked() {
        mApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankioData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.i("onSubscribe:%s", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull GankioData gankioData) {
                        Logger.i("数据：%s", gankioData.toString());
                        binding.mainHwTv.setBackgroundColor(Color.RED);
                        binding.setGankIo(gankioData);
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
