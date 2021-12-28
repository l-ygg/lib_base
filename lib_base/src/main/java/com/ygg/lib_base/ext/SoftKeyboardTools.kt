
package com.ygg.lib_base.ext

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

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
/** 根据当前焦点控件[v]、触摸事件[ev]判断是否需要隐藏软键盘 */
fun shouldHideInput(v: View?, ev: MotionEvent): Boolean {
    if (v is EditText) {
        // 是输入框
        val leftTop = intArrayOf(0, 0)
        // 获取输入框当前的位置
        v.getLocationInWindow(leftTop)
        val top = leftTop[1]
        val bottom = top + v.height
        // 触摸位置不在输入框范围内，需要隐藏
        return !(ev.y > top && ev.y < bottom)
    }
    return false
}