package com.ygg.module_login

import com.alibaba.android.arouter.facade.annotation.Route
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.module_login.databinding.LoginActivityTestBinding
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
@Route(path = "/main/TestFragment")
class TestFragment: BaseFragment<TestViewModel, LoginActivityTestBinding>() {

    override val viewModel: TestViewModel by viewModel()

    override fun initContentView(): Int =R.layout.login_activity_test

    override fun initData() {
    }

    override fun initViewObservable() {
    }

    override fun useBaseLayout(): Boolean {
        return true
    }
}