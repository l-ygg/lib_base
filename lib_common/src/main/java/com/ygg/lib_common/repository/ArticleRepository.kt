package com.ygg.lib_common.repository

import com.ygg.lib_base.net.netRequest
import com.ygg.lib_common.net.ApiService

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/6
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class ArticleRepository(private val apiService: ApiService) {

    /** 获取首页 Banner 列表 */
    suspend fun getHomepageBannerList() = netRequest {
        apiService.getHomepageBannerList()
    }

}