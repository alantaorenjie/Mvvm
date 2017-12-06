package com.trj.mvvmdemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.trj.mvvmdemo.BR;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public abstract class BaseActivity<VM extends BaseViewModule> extends DaggerAppCompatActivity {

    public VM mViewModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        ButterKnife.bind(this);
        mViewModule.onCreateView();
        init();
    }

    private void initViewDataBinding() {
        ViewDataBinding viewDatabinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewDatabinding.setVariable(BR.vm, mViewModule = getViewModule());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModule.onDestroyView();
    }

    /**
     * 获取ViewModule
     * @return 返回当前activity的ViewModule
     */
    protected abstract VM getViewModule();

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
