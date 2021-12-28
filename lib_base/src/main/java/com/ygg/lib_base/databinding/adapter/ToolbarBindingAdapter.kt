package com.ygg.lib_base.databinding.adapter

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
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


/** 为 [toolbar] 设置菜单点击监听 [itemClick] */
@BindingAdapter("android:bind_toolbar_itemClick")
fun setToolbarMenuItemClick(toolbar: Toolbar, itemClick: ((MenuItem) -> Boolean)?) {
    toolbar.setOnMenuItemClickListener(itemClick)
}

/** 为 [toolbar] 设置导航按钮点击监听 [click] */
@BindingAdapter("android:bind_toolbar_navigationClick")
fun setToolbarNavigationClick(toolbar: Toolbar, click: (() -> Unit)?) {
    toolbar.setNavigationOnClickListener { click?.invoke() }
}

/** 将 [toolbar] 标题设置为 [title] */
@BindingAdapter("android:bind_toolbar_title")
fun setToolbarTitle(toolbar: Toolbar, title: String?) {
    toolbar.title = title
}