package com.ygg.lib_base.databinding.adapter

import androidx.core.widget.NestedScrollView
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




/**
 * 为 [scrollView] 设置滚动监听 [onScroll]
 * > [onScroll]: (`scrollY`: [Int], `oldScrollY`: [Int]) -> [Unit]
 *
 * > `scrollY`: 当前垂直滚动原点 & `oldScrollY`: 先前垂直滚动原点
 */
@BindingAdapter("android:bind_nested_onScrollChange")
fun setOnScrollChangeListener(scrollView: NestedScrollView, onScroll: ((Int, Int) -> Unit)?) {
    if (null == onScroll) {
        scrollView.setOnScrollChangeListener(null as? NestedScrollView.OnScrollChangeListener?)
        return
    }
    scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
        onScroll.invoke(scrollY, oldScrollY)
    })
}

/** 为 [scrollView] 设置滚动监听 [onScroll] */
@BindingAdapter("android:bind_nested_onScrollChange")
fun setOnScrollChangeListener(scrollView: NestedScrollView, onScroll: NestedScrollListener?) {
    scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
        onScroll?.onScrollChange(scrollY, oldScrollY)
    })
}

/** 滚动监听接口，提供 [onScrollChange] 回调 */
interface NestedScrollListener {

    /** 滚动回调，传递当前垂直滚动原点 [scrollY] 以及先前垂直滚动原点 [oldScrollY] */
    fun onScrollChange(scrollY: Int, oldScrollY: Int)
}