package com.ygg.lib_base.databinding.adapter

import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ygg.lib_base.ext.orTrue

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/5
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
/**
 * BottomNavigationView DataBinding 适配器
 */

/**
 * 为 [bnv] 设置条目选中回调[itemSelected]
 * > [itemSelected] 传递 [Int] 类型 **itemId**，返回 [Boolean] 类型 是否消费事件
 *
 * > [listener] 为属性变化监听，`DataBinding` 自动实现，无需配置
 */
@BindingAdapter(
    "android:bind_bnv_onItemSelected",
    "android:bind_bnv_selectedIdAttrChanged",
    requireAll = false
)
fun setOnNavigationItemSelectedListener(
    bnv: BottomNavigationView,
    itemSelected: ((Int) -> Boolean)?,
    listener: InverseBindingListener?
) {
    bnv.setOnNavigationItemSelectedListener {
        if (bnv.selectedItemId != it.itemId) {
            listener?.onChange()
        }
        itemSelected?.invoke(it.itemId).orTrue()
    }
}

/**
 * 设置 [bnv] 选中指定 [selectedId] 的条目
 */
@BindingAdapter("android:bind_bnv_selectedId")
fun setNavigationSelectedId(bnv: BottomNavigationView, @IdRes selectedId: Int) {
    bnv.selectedItemId = selectedId
}

/**
 * 获取并返回 [bnv] 当前选中 item 的 id，[Int] 类型
 */
@InverseBindingAdapter(attribute = "android:bind_bnv_selectedId")
fun getNavigationSelectedId(bnv: BottomNavigationView): Int {
    return bnv.selectedItemId
}