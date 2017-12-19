package com.trj.mvvmdemo.ui.vm;

import com.trj.mvvmdemo.ui.activity.MainActivity;
import com.trj.mvvmdemo.ui.base.BaseViewModule;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public class MainViewModule extends BaseViewModule{

    private MainActivity mMainActivity;

    @Inject
    public SubMainViewModule subMainViewModule;

    @Inject
    public MainViewModule(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }
}
