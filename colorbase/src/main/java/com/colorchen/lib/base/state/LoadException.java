package com.colorchen.lib.base.state;

/**
 * 加载异常
 *
 * Author ChenQ on 2017/8/11
 * email：wxchenq@yutong.com
 */
public class LoadException extends Exception{
    private int code;

    public LoadException(@LoadState int code) {
        super();
        this.code = code;
    }


    @LoadState
    public int getCode() {
        return code;
    }

    public void setCode(@LoadState int code) {
        this.code = code;
    }
}
