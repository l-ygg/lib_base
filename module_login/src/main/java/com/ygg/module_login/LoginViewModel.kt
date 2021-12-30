package com.ygg.module_login

import androidx.databinding.ObservableField
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.model.UiLoadingDialogModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.util.toast.showSuccessToast

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
class LoginViewModel : BaseViewModel() {

    /** 用户名 */
    val userName: ObservableField<String> = ObservableField("test")

    /** 指纹登录点击 */
    val onLoginClick: () -> Unit = {
        uiStartContainerActivity.value= UiStartActModel("/main/TestFragment")
    }
}