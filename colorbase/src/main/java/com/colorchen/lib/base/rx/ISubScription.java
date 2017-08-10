package com.colorchen.lib.base.rx;

import io.reactivex.disposables.Disposable;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public interface ISubScription {
    void addSubscription(Disposable disposable);
    void removeSubscription();
}
