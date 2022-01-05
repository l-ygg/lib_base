package com.ygg.module_login.ui

import android.graphics.Color
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_common.constants.ROUTER_PATH_LOGIN
import com.ygg.lib_common.event.RegisterSuccessEvent
import com.ygg.module_login.viewmodel.LoginViewModel
import com.ygg.module_login.R
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
@Route(path = ROUTER_PATH_LOGIN)
class LoginActivity : BaseActivity<LoginViewModel, LoginActivityLoginBinding>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun useBaseLayout(): Boolean {
        return false
    }

    override fun initContentView(): Int = R.layout.login_activity_login

    override fun initData() {

        binding.etAccount.addTextChangedListener {
            checkInput()
        }

        binding.etPwd.addTextChangedListener {
            checkInput()
        }
    }

    override fun initViewObservable() {

        LiveEventBus.get(RegisterSuccessEvent::class.java).observe(this, {
            binding.etAccount.setText(it.account)
            binding.etPwd.setText(it.pwd)
        })

    }

    /**
     *  检查内容是否输入完成
     */
    private fun checkInput() {

        var enable: Boolean =
            viewModel.account.get()?.isNotBlank() == true && viewModel.pwd.get()
                ?.isNotBlank() == true
        binding.btnLogin.isEnabled = enable
        binding.btnLogin.setTextColor(
            if (enable) Color.parseColor("#000000") else Color.parseColor("#ffffff")
        )
        binding.btnLogin.setBackgroundResource(
            if (enable) R.drawable.shape_round_white
            else R.drawable.gray_btn_corner_10dp
        )
    }


}