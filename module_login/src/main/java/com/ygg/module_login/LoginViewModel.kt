package com.ygg.module_login

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.cache.decodeString
import com.ygg.lib_base.cache.encode
import com.ygg.lib_base.model.UiLoadingDialogModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.net.request
import com.ygg.lib_base.util.toast.showErrorToast
import com.ygg.lib_base.util.toast.showSuccessToast
import com.ygg.lib_common.constants.A_MAIN
import com.ygg.lib_common.constants.DATA_CACHE_KEY_USER_NAME
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
    val userName: ObservableField<String> = ObservableField("test")

    /** 指纹登录点击 */
    val onLoginClick: () -> Unit = {
//        request({ repository.login("123456", "123456") }, {
//            LogUtils.i("登录成功")
//        })
//        LogUtils.i("保存的用户名:" + DATA_CACHE_KEY_USER_NAME.decodeString())
//        DATA_CACHE_KEY_USER_NAME.encode("123456")

        uiStartActivity.value= UiStartActModel(A_MAIN)
    }
}