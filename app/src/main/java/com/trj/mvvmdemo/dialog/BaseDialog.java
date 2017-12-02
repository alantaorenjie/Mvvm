package com.trj.mvvmdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.trj.mvvmdemo.R;


/**
 * Created by wangjianfeng on 2016/12/16.
 * Description：对话框的基类
 */

abstract class BaseDialog extends Dialog {

    BaseDialog(Context context) {
        this(context, R.style.BaseDialog);
    }

    BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        setContentView(View.inflate(getContext(), getLayoutId(), null));
    }

    protected abstract int getLayoutId();
}
