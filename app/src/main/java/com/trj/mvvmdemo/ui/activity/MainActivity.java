package com.trj.mvvmdemo.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.trj.mvvmdemo.BR;
import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.common.BindingAdapter;
import com.trj.mvvmdemo.di.AppComponent;
import com.trj.mvvmdemo.dialog.NoticeDialog;
import com.trj.mvvmdemo.model.GankioData;
import com.trj.mvvmdemo.model.UserData;
import com.trj.mvvmdemo.ui.base.BaseActivity;
import com.trj.mvvmdemo.ui.vm.MainViewModule;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.Lazy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class MainActivity extends BaseActivity<MainViewModule> {

    @BindView(R.id.main_hw_tv)
    TextView mHwTv;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    OkHttpClient mOkHttp;

    @Inject
    ApiService mApi;

    @Inject
    Lazy<UserData> mUserData;

    @Inject
    AppComponent mAppComponent;

    @Inject
    MainViewModule VM;

    private BindingAdapter mAdapter;

    @Override
    protected MainViewModule getViewModule() {
        return VM;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BindingAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

        Log.i("1234 user", mUserData.get().toString());
        Log.i("1234 mAppComponent", mAppComponent.toString());
        Log.i("1234 mainActivity", mOkHttp.toString()+","+ mApi.toString());
        mHwTv.setText(mUserData.toString());
    }


    @OnClick(R.id.main_hw_tv)
    public void onViewClicked() {
        new NoticeDialog(this).show();
        mUserData.get().getData();
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

//                        mViewDatabinding.setVariable(BR.data, gankioData);

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
                                    for (GankioData.ResultsBean bean : gankioData.results) {
                                        bean.setWho(String.format(Locale.getDefault(), "%s  %s", bean.desc, Math.random() * 10));
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
