package com.thugkd.wanandroid.base

import android.app.Application
import android.content.ComponentCallbacks2
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils
import com.bumptech.glide.Glide
import com.squareup.leakcanary.LeakCanary
import com.thugkd.wanandroid.BuildConfig

/**
 * @author : Administrator
 * @date : 2019/5/27.
 */
class WanAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }

        SPStaticUtils.setDefaultSPUtils(SPUtils.getInstance(Constant.SHARE_NAME))
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }

        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }
}