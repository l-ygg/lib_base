package com.ygg.module_main.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.request
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.BannerEntity
import com.ygg.lib_common.interfaces.collect
import com.ygg.lib_common.repository.ArticleRepository

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
class HomeViewModel(private val repository: ArticleRepository) :
    BaseViewModel() {

    /** 页码 */
    val pageNumber: MutableLiveData<Int> = MutableLiveData(NET_PAGE_START)

    /**
     *  博文数据源
     */
    val articleData: MutableLiveData<List<ArticleEntity>> = MutableLiveData()

    /** Banner 列表数据 */
    val bannerData: MutableLiveData<List<BannerEntity>> = MutableLiveData()

    /**
     *  获取banner列表
     */
    fun getBannerList() {
        request({ repository.getHomepageBannerList() },
            success = {
                bannerData.value = it
            })
    }


    /** ViewPage 当前位置 */
    val tabItem: ObservableInt = ObservableInt(0)

    val onItemSelected: (Int) -> Boolean = {
        tabItem.set(it)
        true
    }

    /**
     *  获取首页博文列表
     */
    fun getHomeArticle(page: Int) {
        request({ repository.getHomepageArticleList(pageNumber.value!!) }, success = {
            articleData.value = it.datas
        })
    }

    /**
     *  收藏或取消收藏
     */
    fun articleCollect(bean: ArticleEntity) {
        collect(repository, bean)
    }

}