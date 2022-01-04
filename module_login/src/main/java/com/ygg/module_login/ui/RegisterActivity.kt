package com.ygg.module_login.ui

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_common.constants.ROUTER_PATH_REGISTER
import com.ygg.module_login.R
import com.ygg.module_login.databinding.LoginActivityRegisterBinding
import com.ygg.module_login.viewmodel.LoginViewModel
import com.ygg.module_login.viewmodel.RegisterViewModel
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
@Route(path = ROUTER_PATH_REGISTER)
class RegisterActivity : BaseActivity<RegisterViewModel, LoginActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModel()

    override fun initContentView(): Int = R.layout.login_activity_register

    override fun initData() {

        initBackBtnMargin()

        binding.etAccount.addTextChangedListener {
            checkInput()
        }

        binding.etPwd.addTextChangedListener {
            checkInput()
        }

        binding.etPwdConfirm.addTextChangedListener {
            checkInput()
        }
    }

    override fun initViewObservable() {
    }

    override fun useBaseLayout(): Boolean {
        return false
    }

    private fun checkInput() {
        LogUtils.i("pwd:" + viewModel.pwd.get() + "   repwd:" + viewModel.rePwd.get())
        var enable: Boolean = viewModel.account.get()?.isNotBlank() == true
                && viewModel.pwd.get()?.isNotBlank() == true
                && viewModel.pwd.get().toString() == viewModel.rePwd.get().toString()

        binding.btnRegister.isEnabled = enable
        binding.btnRegister.setTextColor(
            if (enable) Color.parseColor("#000000") else Color.parseColor("#ffffff")
        )
        binding.btnRegister.setBackgroundResource(
            if (enable) R.drawable.shape_round_white
            else R.drawable.gray_btn_corner_10dp
        )
    }

    private fun initBackBtnMargin() {
        val layoutParams = binding.ivBack.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.setMargins(
            layoutParams.leftMargin,
            BarUtils.getStatusBarHeight(),
            layoutParams.rightMargin,
            layoutParams.bottomMargin
        )
        binding.ivBack.layoutParams = layoutParams
    }
}