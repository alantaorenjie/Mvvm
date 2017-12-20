package com.trj.mvvmdemo.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trj.mvvmdemo.BR;

import dagger.android.support.AndroidSupportInjection;

/**
 * @author TRJ
 * @date 2017/12/20
 * Description:
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseFragmentViewModule> extends Fragment {

    protected B mBinding;

    protected VM mViewModule;

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    /**
     * 这种情况是为了防止内存重启的情况下，用getActivity() = null
     * 这样处理有引起内存泄漏的风险，但是异步任务没有停止的情况下
     * 本身可能就已经引起内存泄漏，这样处理更‘安全’些
     */
    public BaseActivity mActivity;

    protected abstract int getLayoutId();

    protected abstract VM getViewModule();

    protected abstract void init();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        /*
         * 以下代码可以防止在内存重启的情况下，通过add show hide
         * 方式管理Fragment的情况下，Fragment重叠的情况
         *
         * 因为内存重启的时候，FragmentManager会从栈底到栈顶
         * 的顺序一次性恢复Fragment
         */
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mBinding.setVariable(BR.vm, mViewModule = getViewModule());
        init();
        return mBinding.getRoot();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    /**
     * 显示Toast
     *
     * @param message message
     */
    public void showToast(String message) {
        if (mActivity != null) {
            mActivity.showToast(message);
        }
    }

    public void showToast(int redId) {
        if (mActivity != null) {
            mActivity.showToast(mActivity.getString(redId));
        }
    }
}
