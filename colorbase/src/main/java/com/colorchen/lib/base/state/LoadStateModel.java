package com.colorchen.lib.base.state;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.colorchen.lib.base.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * loading setting model
 * Author ChenQ on 2017/8/11
 * email：wxchenq@yutong.com
 */
public class LoadStateModel extends BaseObservable{
    private Context mContext;
    
    public LoadStateModel(Context context){
        this.mContext = context;
    }

    @LoadState
    private int loadState = LoadState.DEFAULT;

    private CallBack mCallBack;

    public int getLoadState() {
        return loadState;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyChange();
    }

    /**
     * 根据异常显示状态
     */
    public void bindThrowable(Throwable e) {
        bindThrowable(e, true);
    }

    /**
     * 提示异常错误
     *
     * @param e            异常信息
     * @param showNetError 是否显示除空数据异常的其它信息
     */
    public void bindThrowable(Throwable e, boolean showNetError) {

        if (e instanceof LoadException) {
            if (((LoadException) e).getCode() == LoadState.NET_ERROR) {
                bindThrowable(new ConnectException());
            } else {
                setLoadState(((LoadException) e).getCode());
            }
        } else if (e instanceof SocketTimeoutException) {
            if (mCallBack != null) {
                mCallBack.onFailure(new Throwable("网络连接超时"));
            }
        } else if (e instanceof UnknownHostException || e instanceof ConnectException) {
            //网络未连接
            if (mCallBack != null) {
                mCallBack.onFailure(new Throwable("网络未连接"));
            }
            if (showNetError) {
                setLoadState(LoadState.NET_ERROR);
            }
        } else {
            if (mCallBack != null) {
                mCallBack.onFailure(e);
            }
        }

    }

    //重新加载
    public void reload() {
        if (mCallBack != null) {
            mCallBack.onReload();
        }
    }

    /**
     * 显示进度条
     */
    public boolean isProgress() {
        return this.loadState == LoadState.LOADING;
    }

    /**
     * 显示重新加载
     */
    public boolean isNetError() {
        return this.loadState == LoadState.NET_ERROR;
    }

    /**
     * 显示空视图
     */
    public boolean isEmpty() {
        return this.loadState != LoadState.EMPTY;
    }
    /**
     * 初始状态隐藏
     */
    public boolean isInit() {
        return this.loadState == LoadState.INIT;
    }

    /**
     * 空状态信息
     */
    @Bindable
    public String getCurrentStateLabel() {

        switch (loadState) {
            case LoadState.EMPTY:
                return mContext.getString(R.string.load_no_data_text);
            case LoadState.NET_ERROR:
                return mContext.getString(R.string.load_net_error_text);
            default:
                return "";
        }
    }

    /**
     * 空状态图片
     */
    @Bindable
    public Drawable getEmptyIconRes() {
        switch (loadState) {
            case LoadState.EMPTY:
                return ContextCompat.getDrawable(mContext,
                        android.R.drawable.stat_notify_error);
            case LoadState.NET_ERROR:
                return ContextCompat.getDrawable(mContext, android.R.drawable.star_big_off);
            default:
                return null;
        }
    }


    public void attach(CallBack callBack) {
        mCallBack = callBack;
    }

    public void detach() {
        this.mCallBack = null;
    }


    public interface CallBack {
        //失败
         void onFailure(Throwable e);

        //重新加载
        void onReload();
    }
}
