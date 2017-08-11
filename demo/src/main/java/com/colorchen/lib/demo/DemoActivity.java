package com.colorchen.lib.demo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.colorchen.lib.base.BaseActivityViewLoading;
import com.colorchen.lib.base.router.RouterMap;

/**
 * Author ChenQ on 2017/8/11
 * email：wxchenq@yutong.com
 */
@Route(path = RouterMap.DEMO_MAIN)
public class DemoActivity extends BaseActivityViewLoading {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
    }


    @Override
    protected void loadData(boolean isRefresh) {

    }

}
