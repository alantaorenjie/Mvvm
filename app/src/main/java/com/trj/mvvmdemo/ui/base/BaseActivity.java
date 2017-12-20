package com.trj.mvvmdemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.trj.mvvmdemo.BR;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModule> extends DaggerAppCompatActivity {

    public VM mViewModule;
    public B mBinding;

    public Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        ButterKnife.bind(this);
        mViewModule.onCreateView();
        init();
    }

    private void initViewDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBinding.setVariable(BR.vm, mViewModule = getViewModule());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModule.onDestroyView();
    }

    /**
     * 获取布局资源id
     *
     * @return 当前activity的layout id
     */
    protected abstract int getLayoutId();

    /**
     * 获取ViewModule
     *
     * @return 返回当前activity的ViewModule
     */
    protected abstract VM getViewModule();

    /**
     * 初始化activity
     */
    protected abstract void init();


    public void showToast(String toast){
        if (mToast == null){
            mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        }
        if (TextUtils.isEmpty(toast)){
            return;
        }
        mToast.setText(toast);
        mToast.show();
    }
}
