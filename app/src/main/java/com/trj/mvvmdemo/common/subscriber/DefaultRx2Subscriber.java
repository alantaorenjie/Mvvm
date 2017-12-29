package com.trj.mvvmdemo.common.subscriber;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;

/**
 * @author TRJ
 * @date 2017/12/19
 * Description:
 */
public class DefaultRx2Subscriber<T>  extends DefaultObserver<T> {

    @Override
    public void onNext(@NonNull T o) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
