package com.colorchen.lib.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.colorchen.lib.base.event.ExitAppEvent;
import com.colorchen.lib.base.state.LoadStateModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.Set;

/**
 * Author ChenQ on 2017/8/11
 * email：wxchenq@yutong.com
 */
public abstract class BaseActivityViewLoading<SV extends ViewDataBinding> extends AppCompatActivity implements
        LoadStateModel.CallBack,LifecycleRegistryOwner {
    protected LoadStateModel mLoadStateModel;
    //lifecycle
    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    private Set<BaseViewModel> vmSet = new HashSet<>();
    //布局
    protected SV bindingView;

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void init();

    protected <T extends BaseViewModel> T getViewModel(Class<T> tClass) {
        T baseViewModel = ViewModelProviders.of(this).get(tClass);
        vmSet.add(baseViewModel);
        return baseViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingView = DataBindingUtil.setContentView(this, getLayoutId());
        mLoadStateModel = new LoadStateModel(this);
        mLoadStateModel.attach(this);

        EventBus.getDefault().register(this);
        init();
        loadData(true);//加载数据
    }


    @Override
    public void onFailure(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReload() {
        loadData(true);
    }
    protected abstract void loadData(boolean isRefresh);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //注销
        for (BaseViewModel viewModel : vmSet)
            viewModel.removeSubscription();
        if (mLoadStateModel != null) {
            mLoadStateModel.detach();
        }
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    protected void addViewModel(BaseViewModel baseViewModel){
        vmSet.add(baseViewModel);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void exitApp(ExitAppEvent exitAppEvent) {
        finish();
    }
}
