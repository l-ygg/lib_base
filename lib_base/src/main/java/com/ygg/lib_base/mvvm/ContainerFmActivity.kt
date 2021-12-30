package com.ygg.lib_base.mvvm

import android.os.Bundle
import com.ygg.lib_base.R
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_base.databinding.BaseActivityContainerBinding
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_base.viewmodel.BlankViewModel
import me.yokeyword.fragmentation.SupportFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/30
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class ContainerFmActivity : BaseActivity<BlankViewModel, BaseActivityContainerBinding>() {

    companion object {
        const val FRAGMENT = "fragment"
        const val BUNDLE = "bundle"
    }

    override val viewModel: BlankViewModel by viewModel()
    override fun initContentView(): Int = R.layout.base_activity_container

    override fun initData() {
        val fragmentPath: String? = intent.getStringExtra(FRAGMENT)
        if (fragmentPath.isNullOrBlank()) {
            return
        }
        val args: Bundle? = intent.getBundleExtra(BUNDLE)
        val fragment: SupportFragment = RouteCenter.navigate(fragmentPath,args) as SupportFragment
        if (findFragment(fragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, fragment)
        }
    }

    override fun initViewObservable() {
    }

    override fun useBaseLayout(): Boolean {
        return false
    }
}