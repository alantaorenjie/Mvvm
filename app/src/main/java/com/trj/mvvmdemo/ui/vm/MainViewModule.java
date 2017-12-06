package com.trj.mvvmdemo.ui.vm;

import android.content.Context;

import com.trj.mvvmdemo.ui.activity.MainActivity;
import com.trj.mvvmdemo.ui.base.BaseActivity;
import com.trj.mvvmdemo.ui.base.BaseViewModule;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public class MainViewModule extends BaseViewModule{

    private Context mContext;

    @Inject
    public MainViewModule(MainActivity baseActivity) {
        this.mContext = baseActivity;
    }
}
