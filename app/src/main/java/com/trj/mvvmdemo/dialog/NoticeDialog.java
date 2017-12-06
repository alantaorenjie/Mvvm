package com.trj.mvvmdemo.dialog;

import android.content.Context;
import android.util.Log;

import com.trj.mvvmdemo.ui.base.App;
import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.api.ApiService;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
public class NoticeDialog extends BaseDialog {
    @Inject
    ApiService mApiService;

    public NoticeDialog(Context context) {
        super(context);
        App.mAppComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_notice;
    }


    @Override
    public void show() {
        super.show();
        if (mApiService != null) {
            Log.i("1234 dialog", mApiService.toString());
        }
    }
}
