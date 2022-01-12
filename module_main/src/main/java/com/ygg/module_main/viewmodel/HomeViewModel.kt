package com.ygg.module_main.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.databinding.adapter.SmartRefreshState
import com.ygg.lib_base.ext.orElse
import com.ygg.lib_base.net.request
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.BannerEntity
import com.ygg.lib_common.entity.ProjectEntity
import com.ygg.lib_common.interfaces.collect
import com.ygg.lib_common.interfaces.loadComplete
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

    /**
     *  热门项目列表page
     */
    val projectPageNumber: MutableLiveData<Int> = MutableLiveData(0)

    /**
     *  博文数据源
     */
    val articleData: MutableLiveData<List<ArticleEntity>> = MutableLiveData()

    val projectData: MutableLiveData<List<ProjectEntity>> = MutableLiveData()

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
    fun getHomeArticle() {
        request({ repository.getHomepageArticleList(pageNumber.value!!) }, success = {
            articleData.value = it.datas
            loadComplete(success = true, noMore = it.over.toBoolean())
        }, error = {
            loadComplete(false)
        })
    }

    /**
     *  获取首页热门项目列表
     */
    fun getHomeArticleProject() {
        request({ repository.getHomepageArticleProjectList(projectPageNumber.value!!) },
            success = {
                projectData.value = it.datas
                loadComplete(success = true, noMore = it.over)
            }, error = {
                loadComplete(false)
            })
    }

    /**
     *  收藏或取消收藏
     */
    fun articleCollect(bean: ArticleEntity) {
        collect(repository, bean)
    }

    override fun refresh() {
        super.refresh()
        if (tabItem.get() == 0) {
            getHomeArticle()
        } else {
            projectPageNumber.value = NET_PAGE_START
            getHomeArticleProject()
        }
    }

    override fun loadMore() {
        super.loadMore()
        if (tabItem.get() == 0) {
            getHomeArticle()
        } else {
            projectPageNumber.value = projectPageNumber.value.orElse(NET_PAGE_START + 1)
            getHomeArticleProject()
        }
    }

}