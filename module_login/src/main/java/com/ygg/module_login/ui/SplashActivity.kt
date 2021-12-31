package com.ygg.module_login.ui

import androidx.lifecycle.lifecycleScope
import coil.load
import com.gyf.immersionbar.ImmersionBar
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.ROUTER_PATH_LOGIN
import com.ygg.lib_common.constants.SPLASH_DELAY_MS
import com.ygg.module_login.R
import com.ygg.module_login.databinding.LoginActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/31
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class SplashActivity : BaseActivity<BlankViewModel, LoginActivitySplashBinding>() {

    override val viewModel: BlankViewModel by viewModel()
    override fun initContentView(): Int = R.layout.login_activity_splash

    private val arrayDark =
        arrayListOf(R.mipmap.splash_bg_dark, R.mipmap.splash_bg, R.mipmap.splash_bg_light)

    override fun initData() {
        ImmersionBar.hideStatusBar(window)
        binding.ivSplash.load(arrayDark[Random().nextInt(arrayDark.size)])

        lifecycleScope.launch {
            // 延时 1000ms
            delay(SPLASH_DELAY_MS)
            // 跳转登录界面
            viewModel.uiStartActivity.value = UiStartActModel(ROUTER_PATH_LOGIN)
            // 关闭当前界面
            finish()
        }
    }

    override fun initViewObservable() {
    }

    override fun useBaseLayout(): Boolean {
        return false
    }

}