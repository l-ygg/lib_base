package com.ygg.module_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.request
import com.ygg.lib_common.entity.CategoryEntity
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
class ProjectViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    /** 项目分类数据 */
    val listData = MutableLiveData<ArrayList<CategoryEntity>>()

    fun getProjectCategory() {
        request({ repository.getProjectCategory() }, success = {
            listData.value = it
        })
    }
}