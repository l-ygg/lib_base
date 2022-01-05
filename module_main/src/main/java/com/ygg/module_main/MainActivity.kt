package com.ygg.module_main

import com.alibaba.android.arouter.facade.annotation.Route
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_common.constants.ROUTER_PATH_MAIN
import com.ygg.module_main.MainViewModel
import com.ygg.module_main.R
import com.ygg.module_main.databinding.MainActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/4
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Route(path = ROUTER_PATH_MAIN)
class MainActivity : BaseActivity<MainViewModel, MainActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()
    override fun initContentView(): Int = R.layout.main_activity_main

    override fun initData() {
    }

    override fun initViewObservable() {
    }

}