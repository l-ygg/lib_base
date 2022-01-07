package com.ygg.lib_common.interfaces

import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.AppException
import com.ygg.lib_base.net.BaseResponse
import com.ygg.lib_base.net.request
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.repository.ArticleRepository
import kotlinx.coroutines.Job

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
fun BaseViewModel.collect(
    repository: ArticleRepository,
    bean: ArticleEntity
) {
    bean.id?.let {
        when (bean.collected.get()) {
            true -> {
                request({ repository.collectArticleInside(bean.id) }, success = {
                    bean.collected.set(true)
                })
            }
            else -> {
                request({ repository.unCollectArticleList(bean.id) }, success = {
                    bean.collected.set(false)
                })
            }
        }
    }
}