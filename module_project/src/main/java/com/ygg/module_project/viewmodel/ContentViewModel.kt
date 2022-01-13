package com.ygg.module_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.request
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.CategoryEntity
import com.ygg.lib_common.interfaces.loadComplete
import com.ygg.lib_common.repository.ArticleRepository

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/13
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class ContentViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    /** 项目分类 id */
    var categoryId = ""

    /** 项目列表 */
    val listData = MutableLiveData<ArrayList<ArticleEntity>>()

    /**
     *  获取项目列表
     */
    fun getProjectList() {
        request({ repository.getProjectList(categoryId, pageNumber.value!!) }, success = {
            listData.value = it.datas
            loadComplete(success = true, noMore = it.over.toBoolean())
        }, error = {
            loadComplete(false)
        })
    }

    override fun refresh() {
        super.refresh()
        getProjectList()
    }

    override fun loadMore() {
        super.loadMore()
        getProjectList()
    }
}