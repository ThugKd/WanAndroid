package com.thugkd.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author : Administrator
 * @date : 2019/5/27.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}