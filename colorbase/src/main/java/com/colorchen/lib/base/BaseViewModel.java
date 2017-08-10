package com.colorchen.lib.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.colorchen.lib.base.rx.ISubScription;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public class BaseViewModel extends AndroidViewModel implements ISubScription {
    private CompositeDisposable composite;

    public BaseViewModel(Application application) {
        super(application);
    }

    @Override
    public void addSubscription(Disposable baseSubscriber) {
        if (null == composite)
            composite = new CompositeDisposable();

        composite.add(baseSubscriber);
    }

    @Override
    public void removeSubscription() {
        if (this.composite != null)
            this.composite.clear();
    }
}
