package com.trj.mvvmdemo.ui.activity;

import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.common.ViewPagerFragmentAdapter;
import com.trj.mvvmdemo.databinding.ActivityMainBinding;
import com.trj.mvvmdemo.di.AppComponent;
import com.trj.mvvmdemo.model.UserData;
import com.trj.mvvmdemo.ui.base.BaseActivity;
import com.trj.mvvmdemo.ui.base.BaseFragment;
import com.trj.mvvmdemo.ui.fragment.IndexFragment;
import com.trj.mvvmdemo.ui.vm.MainViewModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import okhttp3.OkHttpClient;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModule> {

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModule getViewModule() {
        return VM;
    }

    @Override
    protected void init() {

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(IndexFragment.newInstance());
        fragments.add(IndexFragment.newInstance());
        fragments.add(IndexFragment.newInstance());
        List<String> titles = new ArrayList<>();
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");

        ViewPagerFragmentAdapter mAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragments,titles);
        mBinding.viewpager.setAdapter(mAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewpager);

    }
}
