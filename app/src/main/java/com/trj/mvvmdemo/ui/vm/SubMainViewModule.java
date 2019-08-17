package com.trj.mvvmdemo.ui.vm;

import com.trj.mvvmdemo.ui.activity.MainActivity;
import com.trj.mvvmdemo.ui.base.BaseViewModule;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/7
 * Description:
 */
public class SubMainViewModule extends BaseViewModule {

    public String tips = "SubMainViewModule";

    @Inject
    public SubMainViewModule(MainActivity mainActivity) {
        super(mainActivity);
        showToast("SubMainViewModule");
    }
}
