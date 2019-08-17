package com.trj.mvvmdemo.binding.viewadapter.viewgroup;

import android.databinding.ViewDataBinding;

/**
 * @author TRJ
 * @date 2017/12/19
 * Description:
 */
public interface IBindingItemViewModel<V extends ViewDataBinding> {
    void injecDataBinding(V binding);
}
