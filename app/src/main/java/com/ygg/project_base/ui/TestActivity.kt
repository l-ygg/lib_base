package com.ygg.project_base.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.project_base.AppConfig
import com.ygg.project_base.R
import com.ygg.project_base.databinding.ActivityTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Route(path = "/main/TestActivity")
class TestActivity : BaseActivity<BlankViewModel, ActivityTestBinding>() {

    override val viewModel: BlankViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

}