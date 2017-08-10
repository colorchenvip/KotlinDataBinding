package com.colorchen.lib.base;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {
    protected abstract void onHandleSuccess(T t);
    protected abstract void onHandleError(Throwable e);

    @Override
    public void onNext(T t) {
        onHandleSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        onHandleError(t);
    }

    @Override
    public void onComplete() {

    }
}
