package com.thugkd.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.blankj.utilcode.util.SPStaticUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.thugkd.wanandroid.R
import com.thugkd.wanandroid.base.BaseActivity
import com.thugkd.wanandroid.base.Constant
import com.thugkd.wanandroid.ui.fragment.CommonUserFragment
import com.thugkd.wanandroid.ui.fragment.HomeFragment
import com.thugkd.wanandroid.ui.fragment.TypeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var lastTime: Long = 0
    private var currentIndex = 0
    private var homeFragment: HomeFragment? = null
    private var typeFragment: TypeFragment? = null
    private var commonUserFragment: CommonUserFragment? = null
    private val fragmentManager by lazy {
        supportFragmentManager
    }
    private val isLogin = SPStaticUtils.getBoolean(Constant.LOGIN)
    private val username: String = SPStaticUtils.getString(Constant.USERNAME, "")
    private lateinit var navigationViewUsername: TextView
    private lateinit var nativationViewLogout: AppCompatButton

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        bottomNavigation.run {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_home
        }
        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }
        navigationView.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }
    }


    private val onDrawerNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_like -> {
                    if (!isLogin) {
                        Intent(this, LoginActivity::class.java).run {
                            startActivityForResult(this, Constant.REQUEST_LOGIN)
                        }
                        return@OnNavigationItemSelectedListener true
                    }

                    Intent(this, SearchActivity::class.java).run {
                        putExtra(Constant.SEARCH_KEY, false)
                        startActivityForResult(this, Constant.REQUEST_LIKE)
                    }
                }
                R.id.nav_about -> {
                    Intent(this, AboutActivity::class.java).run {
                        startActivity(this)
                    }
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            setFragment(item.itemId)
            return@OnNavigationItemSelectedListener when (item.itemId) {
                R.id.navigation_home -> {
                    if (currentIndex == R.id.navigation_home) {
//                        homeFragment?.smoothScrollToPosition()
                    }
                    currentIndex = R.id.navigation_home
                    true
                }
                R.id.navigation_type -> {
                    if (currentIndex == R.id.navigation_type) {
//                        typeFragment?.smoothScrollToPosition()
                    }
                    currentIndex = R.id.navigation_type
                    true
                }
                else -> {
                    false
                }
            }
        }

    private fun setFragment(index: Int) {

    }

    override fun cancelRequest() {
    }


}
