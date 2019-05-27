package com.thugkd.wanandroid.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.KeyboardUtils
import com.gyf.immersionbar.ImmersionBar

/**
 * @author : Administrator
 * @date : 2019/5/27.
 */

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var immersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        initImmersionBar()
    }

    protected abstract fun setLayoutId(): Int

    protected open fun initImmersionBar() {
        immersionBar = ImmersionBar.with(this)
        immersionBar.init()
    }

    protected abstract fun cancelRequest()

    override fun onDestroy() {
        super.onDestroy()
        cancelRequest()
    }

    override fun finish() {
        if (!isFinishing) {
            super.finish()
            KeyboardUtils.hideSoftInput(this)
        }
    }
}