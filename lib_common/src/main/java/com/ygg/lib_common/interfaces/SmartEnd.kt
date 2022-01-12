package com.ygg.lib_common.interfaces

import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.databinding.adapter.SmartRefreshState
import com.ygg.lib_common.constants.NET_PAGE_START

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/11
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
fun BaseViewModel.loadComplete(
    success: Boolean = true,
    noMore: Boolean = false
) {
    if (pageNumber.value == NET_PAGE_START) {
        refreshing.value =
            SmartRefreshState(loading = false, success = success, noMore = noMore)
    } else {
        loadMore.value =
            SmartRefreshState(loading = false, success = success, noMore = noMore)
    }
}