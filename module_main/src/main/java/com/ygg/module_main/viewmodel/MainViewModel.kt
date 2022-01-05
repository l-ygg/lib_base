package com.ygg.module_main.viewmodel

import androidx.databinding.ObservableInt
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/4
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class MainViewModel : BaseViewModel() {

    /** ViewPage 当前位置 */
    val currentItem: ObservableInt = ObservableInt(0)

    val onItemSelected: (Int) -> Boolean = {
        currentItem.set(it)
        true
    }

}