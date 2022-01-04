package com.ygg.module_login.viewmodel

import androidx.databinding.ObservableField
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.net.request
import com.ygg.lib_base.util.toast.showSuccessToast
import com.ygg.lib_common.constants.ROUTER_PATH_REGISTER
import com.ygg.lib_common.repository.UserRepository

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
class LoginViewModel(private val repository: UserRepository) : BaseViewModel() {

    /** 用户名 */
    val account: ObservableField<String> = ObservableField()

    /** 密码 */
    val pwd: ObservableField<String> = ObservableField()

    /** 登录点击 */
    val loginClick: () -> Unit = {

        request({ repository.login(account.get().toString(), pwd.get().toString()) }, success = {
            showSuccessToast("登录成功")
        }, isShowDialog = true)

    }

    /** 注册点击 */
    val registerClick: () -> Unit = {
        uiStartActivity.value = UiStartActModel(ROUTER_PATH_REGISTER)
    }

    /** 随便看看点击 */
    val touristClick: () -> Unit = {

    }
}