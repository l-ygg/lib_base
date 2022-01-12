package com.ygg.lib_common.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.databinding.adapter.SmartRefreshState
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.net.BaseBean

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/10
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
interface SmartRefreshInterface {

    /** 页码 */
    val pageNumber: MutableLiveData<Int>

    /** 刷新状态 */
    val refreshing: MutableLiveData<SmartRefreshState>

    /** 加载更多状态 */
    val loadMore: MutableLiveData<SmartRefreshState>

    /** 刷新回调 */
    val onRefresh: () -> Unit

    /** 加载更多回调 */
    val onLoadMore: () -> Unit
}