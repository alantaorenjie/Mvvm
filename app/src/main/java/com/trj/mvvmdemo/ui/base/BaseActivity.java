package com.trj.mvvmdemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    public ViewDataBinding mViewDatabinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDatabinding = DataBindingUtil.setContentView(this, getLayoutId());
        ButterKnife.bind(this);
        init();
    }

    /**
     * 获取布局资源id
     * @return 当前activity的layout id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化activity
     */
    protected abstract void init();
}
