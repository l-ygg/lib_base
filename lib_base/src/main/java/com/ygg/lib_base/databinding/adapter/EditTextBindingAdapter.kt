package com.ygg.lib_base.databinding.adapter

import android.text.method.TransformationMethod
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

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

/** 将 [EditText] 光标移动至 [selection] 位置 */
@BindingAdapter("android:bind_selection")
fun setEditTextSelection(et: EditText, selection: Int?) {
    if (null == selection) {
        return
    }
    et.postDelayed({
        if (selection < et.text.length) {
            et.setSelection(selection)
        }
    }, 200)
}

/** 设置 [EditText] 输入类型为 [inputType] */
@BindingAdapter("android:bind_inputType")
fun setEditTextInputType(et: EditText, inputType: TransformationMethod?) {
    et.transformationMethod = inputType
}

/**
 * 给 [EditText] 设置软键盘事件监听 [action]
 * > [action]: (`v`: [TextView], `actionId`: [Int], `event`: [KeyEvent]?) -> [Boolean]
 *
 * > `v`: [et] 对象 & `actionId`: 动作标记 & `event`: 事件 & 返回：是否消费事件
 */
@BindingAdapter("android:bind_et_onEditorAction")
fun setOnEditorAction(et: EditText, action: ((TextView, Int, KeyEvent?) -> Boolean)?) {
    et.setOnEditorActionListener(action)
}

/**
 * 给 [EditText] 设置软键盘事件监听 [action]
 * > [action]: (`actionId`: [Int]) -> [Boolean]
 *
 * > `actionId`: 动作标记
 */
@BindingAdapter("android:bind_et_onEditorAction")
fun setOnEditorAction(et: EditText, action: ((Int) -> Boolean)?) {
    if (null == action) {
        et.setOnEditorActionListener(null)
        return
    }
    et.setOnEditorActionListener { _, actionId, _ ->
        action.invoke(actionId)
    }
}