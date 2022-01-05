package com.ygg.lib_base.databinding.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ygg.lib_base.ext.orTrue

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：自定义控件的databinding
 * <p>
 * <p>
 * 作者：lengyang 2022/1/5
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */

@BindingAdapter(
    "android:bind_bnv_onCustomItemSelected",
    "android:bind_bnv_customSelectedIdAttrChanged",
    requireAll = false
)
fun onTabChangeCommand(
    bnv: BottomNavigationBar,
    itemSelected: ((Int) -> Boolean)?,
    listener: InverseBindingListener?
) {
    bnv.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
        override fun onTabSelected(position: Int) {
            if (bnv.currentSelectedPosition != position) {
                listener?.onChange()
            }
            itemSelected?.invoke(position).orTrue()
        }

        override fun onTabUnselected(position: Int) {
        }

        override fun onTabReselected(position: Int) {
        }
    })
}