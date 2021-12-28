package com.ygg.lib_base.model

import android.app.Activity
import android.content.Intent

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：关闭 UI 界面数据对象
 *
 * @param resultCode 返回码 默认 [Activity.RESULT_CANCELED]
 * @param result 返回数据 默认 null
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
data class UiCloseModel(
    var resultCode: Int = Activity.RESULT_CANCELED,
    var result: Intent? = null
)