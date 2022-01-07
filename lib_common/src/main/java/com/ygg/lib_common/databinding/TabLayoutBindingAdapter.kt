package com.ygg.lib_common.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.google.android.material.tabs.TabLayout
import com.ygg.lib_base.ext.orTrue

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/7
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@BindingAdapter(
    "android:bind_onTabSelected",
    "android:bind_tabSelectedIdAttrChanged",
    requireAll = false
)
fun onTabSelected(
    tabLayout: TabLayout,
    itemSelected: ((Int) -> Boolean)?,
    listener: InverseBindingListener?
) {
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let {
                if (tabLayout.selectedTabPosition != it.position) {
                    listener?.onChange()
                }
                itemSelected?.invoke(it.position).orTrue()
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

    })
}