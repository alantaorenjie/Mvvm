package com.trj.mvvmdemo.common.subscriber;

import rx.Subscriber;

/**
 * Created by wangjianfeng on 2016/12/12.
 * Description：默认的订阅者，不做任何处理，只打印异常信息，
 * 防止抛出  onError handler exception;
 */

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
