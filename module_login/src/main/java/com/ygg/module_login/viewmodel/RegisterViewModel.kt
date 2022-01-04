package com.ygg.module_login.viewmodel

import androidx.databinding.ObservableField
import com.jeremyliao.liveeventbus.LiveEventBus
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.model.UiCloseModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.net.request
import com.ygg.lib_base.util.toast.showSuccessToast
import com.ygg.lib_common.constants.ROUTER_PATH_REGISTER
import com.ygg.lib_common.event.RegisterSuccessEvent
import com.ygg.lib_common.repository.UserRepository

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
class RegisterViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    /** 用户名 */
    val account: ObservableField<String> = ObservableField()

    /** 密码 */
    val pwd: ObservableField<String> = ObservableField()

    /** 确认密码 */
    val rePwd: ObservableField<String> = ObservableField()

    /** 返回键点击 */
    val backClick: () -> Unit = {
        uiCloseActivity.value = UiCloseModel()
    }

    /** 注册点击 */
    val registerClick: () -> Unit = {
        request(block = {
            repository.register(account.get().toString(), pwd.get().toString())
        }, success = {
            LiveEventBus.get(RegisterSuccessEvent::class.java)
                .post(RegisterSuccessEvent(account.get().toString(), pwd.get().toString()))
            showSuccessToast("注册成功")
            uiCloseActivity.value = UiCloseModel()
        },isShowDialog = true)
    }

}