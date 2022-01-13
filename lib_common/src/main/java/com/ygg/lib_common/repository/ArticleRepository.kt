package com.ygg.lib_common.repository

import androidx.databinding.ObservableBoolean
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.ext.orFalse
import com.ygg.lib_base.net.netRequest
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.lib_common.constants.STR_TRUE
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.SquareEntity
import com.ygg.lib_common.net.ApiService
import kotlinx.coroutines.async

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

    /**
     * 根据页码[pageNum]获取首页文章列表
     * > [pageNum] 为 [NET_PAGE_START] 时，将同时获取置顶数据合并返回
     */
    suspend fun getHomepageArticleList(pageNum: Int) = netRequest {
        // 获取文章列表
        val ls = arrayListOf<ArticleEntity>()
        if (pageNum == NET_PAGE_START) {
            // 刷新获取置顶文章列表
            val tops = async {
                apiService.getHomepageTopArticleList().data.orEmpty()
            }
            tops.await().forEach {
                ls.add(it.copy(top = STR_TRUE))
            }
        }
        // 获取文章列表
        val resultAsync = async {
            apiService.getHomepageArticleList(pageNum)
        }
        val result = resultAsync.await()
        // 添加文章列表到 ls
        ls.addAll(result.data?.datas.orEmpty())
        // 处理收藏状态
        ls.forEach {
            it.collected.set(it.collect?.toBoolean().orFalse())
        }
        // 处理返回列表
        result.copy(data = result.data?.copy(datas = ls))
    }

    /** 通过文章[id]收藏站内文章 */
    suspend fun collectArticleInside(id: String) = netRequest {
        apiService.collectArticleInside(id)
    }

    /** 文章列表中根据文章[id]取消收藏 */
    suspend fun unCollectArticleList(id: String) = netRequest {
        apiService.unCollectArticleList(id)
    }

    /**
     *  获取首页热门项目列表
     */
    suspend fun getHomepageArticleProjectList(pageNum: Int) = netRequest {
        apiService.getHomepageArticleProjectList(pageNum)
    }

    /**
     *  获取广场列表
     */
    suspend fun getSquareList(pageNum: Int) = netRequest {
        val ls = arrayListOf<SquareEntity>()
        val resultAsync = async {
            apiService.getSquareList(pageNum)
        }
        val result = resultAsync.await()
        // 添加文章列表到 ls
        ls.addAll(result.data?.datas.orEmpty())
        // 处理收藏状态
        ls.forEach {
            if (it.collected == null) {
                it.collected=ObservableBoolean()
                LogUtils.i("collect: null")
            } else {
                LogUtils.i("connect:not null")
            }
            it.collected.set(it.collect?.toBoolean().orFalse())
        }
        // 处理返回列表
        result.copy(data = result.data?.copy(datas = ls))
    }
}