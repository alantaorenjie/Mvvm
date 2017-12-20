package com.trj.mvvmdemo.ui.base;

import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public class BaseViewModule implements IBaseViewModule {

    protected BaseActivity mActivity;
    private Toast mToast;

    public BaseViewModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
        init();
    }

    private void init() {
        mToast = Toast.makeText(mActivity,"",Toast.LENGTH_SHORT);
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onDestroyView() {

    }

    public void showToast(String toast){
        if (TextUtils.isEmpty(toast)){
            return;
        }
        mToast.setText(toast);
        mToast.show();
    }


}
