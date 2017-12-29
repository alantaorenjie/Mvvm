package com.trj.mvvmdemo.ui.base;

import android.app.Activity;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/20
 * Description:
 */
public abstract class BaseFragmentViewModule {

    public BaseActivity mActivity;

    public BaseFragmentViewModule(Activity baseActivity) {
        this.mActivity = (BaseActivity) baseActivity;
    }
}
