package com.trj.mvvmdemo.ui.base;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public interface IBaseViewModule {
    /**
     * View的界面创建时回调
     */
    void onCreateView();

    /**
     * View的界面销毁时回调
     */
    void onDestroyView();
}
