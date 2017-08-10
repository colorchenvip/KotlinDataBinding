package com.colorchen.lib.base.rx;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public class RxUtil {
    public static <T> FlowableTransformer<T, T> doInBackground() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
