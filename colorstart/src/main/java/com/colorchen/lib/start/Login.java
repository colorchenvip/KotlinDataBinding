package com.colorchen.lib.start;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.colorchen.lib.base.BaseActivity;
import com.colorchen.lib.base.router.RouterMap;

/**
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
@Route(path = RouterMap.SPLASH)
public class Login extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {

    }
}
