package com.trj.mvvmdemo.common.subscriber;

import rx.Subscriber;


public class DefaultSubscriber<T> extends Subscriber<T> {

    public DefaultSubscriber() {
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T o) {

    }
}
