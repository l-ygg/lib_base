package com.ygg.lib_base.activity

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.ygg.lib_base.R
import com.ygg.lib_base.ext.hideSoftKeyboard
import com.ygg.lib_base.ext.shouldHideInput
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity

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
abstract class BaseLibActivity : SwipeBackActivity() {

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: AppCompatActivity

    /** 标记 - 触摸输入框以外范围是否隐藏软键盘*/
    protected var touchToHideInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 保存当前 Context 对象
        mContext = this

    }

    override fun onPause() {
        super.onPause()
        // 移除当前获取焦点控件的焦点，防止下个界面软键盘顶起布局
        currentFocus?.clearFocus()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (touchToHideInput) {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                if (shouldHideInput(currentFocus, ev)) {
                    // 需要隐藏软键盘
                    currentFocus?.hideSoftKeyboard()
                }
                return super.dispatchTouchEvent(ev)
            }
            if (window.superDispatchTouchEvent(ev)) {
                return true
            }
            return onTouchEvent(ev)
        } else {
            return super.dispatchTouchEvent(ev)
        }
    }
}