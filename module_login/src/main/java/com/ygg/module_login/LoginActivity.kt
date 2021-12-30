package com.ygg.module_login

import com.ygg.lib_base.base.BaseActivity
import com.ygg.module_login.databinding.LoginActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class LoginActivity : BaseActivity<LoginViewModel, LoginActivityLoginBinding>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun useBaseLayout(): Boolean {
        return true
    }

    override fun initContentView(): Int =R.layout.login_activity_login

    override fun initData() {
        viewModel.toolbarTitle.set("登录")
        viewModel.userName.set(TAG)
    }

    override fun initViewObservable() {

    }

}