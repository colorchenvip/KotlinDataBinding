package com.colorchen.lib.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment implements LifecycleRegistryOwner{
    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

}
