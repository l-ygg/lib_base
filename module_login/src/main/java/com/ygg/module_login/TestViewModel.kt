package com.ygg.module_login

import androidx.databinding.ObservableField
import com.blankj.utilcode.util.LogUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.model.UiLoadingDialogModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.util.toast.showSuccessToast
import com.ygg.lib_common.event.EVENT_COLLECTION_CANCELLED

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
class TestViewModel : BaseViewModel() {


    /** 指纹登录点击 */
    val onTestClick: () -> Unit = {
//        uiStartFragment.value= UiStartActModel("/main/TestFragment")
        LiveEventBus.get(EVENT_COLLECTION_CANCELLED).post(TestBean(23))
    }
}