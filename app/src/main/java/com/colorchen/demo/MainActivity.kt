package com.colorchen.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.colorchen.lib.base.router.RouterMap
import com.colorchen.splashlibrary.ColorSplashView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSplash()
    }

     fun initSplash() {
        ColorSplashView.showSplashView(this, 3, com.colorchen.lib.demo.R.drawable.color_splash, object : ColorSplashView.OnSplashViewActionListener {

            override fun onSplashImageClick(actionUrl: String) {
                //  2017/7/27  点击跳转
                ARouter.getInstance().build(RouterMap.DEMO_MAIN).navigation()
                finish()
            }

            override fun onSplashViewDismiss(initiativeDismiss: Boolean) {
                ARouter.getInstance().build(RouterMap.DEMO_MAIN).navigation()
                finish()
            }
        })
        ColorSplashView.updateSplashData(this, "http://ww2.sinaimg.cn/large/72f96cbagw1f5mxjtl6htj20g00sg0vn.jpg", "http://jkyeo.com")
    }
}
