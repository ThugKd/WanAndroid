package com.thugkd.wanandroid.base

import androidx.fragment.app.Fragment

/**
 * @author : Administrator
 * @date : 2019/5/27.
 */
abstract class BaseFragment : Fragment() {
    protected var isFirst = true

    protected abstract fun cancelRequest()

    override fun onDestroy() {
        super.onDestroy()
        cancelRequest()
    }
}