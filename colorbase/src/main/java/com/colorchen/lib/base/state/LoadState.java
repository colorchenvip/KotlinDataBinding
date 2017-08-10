package com.colorchen.lib.base.state;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.colorchen.lib.base.state.LoadState.DEFAULT;
import static com.colorchen.lib.base.state.LoadState.EMPTY;
import static com.colorchen.lib.base.state.LoadState.FAIL;
import static com.colorchen.lib.base.state.LoadState.INIT;
import static com.colorchen.lib.base.state.LoadState.LOADING;
import static com.colorchen.lib.base.state.LoadState.NET_ERROR;
import static com.colorchen.lib.base.state.LoadState.SUCCEED;


/**
 * 自定义的load 状态
 * Author ChenQ on 2017/8/10
 * email：wxchenq@yutong.com
 */
@IntDef({INIT,LOADING,SUCCEED,FAIL,EMPTY,DEFAULT,NET_ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface LoadState {
   int DEFAULT = 0;

   int INIT = 1;
   int LOADING = 2;
   int SUCCEED = 3;

   int FAIL = -1;
   int NET_ERROR = -2;
   int EMPTY = -3;
}
