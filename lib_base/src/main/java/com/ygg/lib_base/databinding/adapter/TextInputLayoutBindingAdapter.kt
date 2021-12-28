package com.ygg.lib_base.databinding.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */


/**
 * 设置错误提示
 */
@BindingAdapter("android:bind_til_error")
fun setError(til: TextInputLayout, error: CharSequence?) {
    if (error.isNullOrBlank()) {
        til.isErrorEnabled = false
        return
    }
    til.error = error
    til.editText?.run {
        if (!isFocusable) {
            isFocusable = true
            isFocusableInTouchMode = true
            requestFocus()
        }
    }
}