package com.trj.mvvmdemo.di;

import com.trj.mvvmdemo.App;
import com.trj.mvvmdemo.dialog.NoticeDialog;
import com.trj.mvvmdemo.model.UserData;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author TRJ
 * @date 2017/12/2
 * Description:
 */
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ObjectModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(NoticeDialog noticeDialog);

    void inject(UserData userData);
}
