package com.ygg.module_square.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.request
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.SquareEntity
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
 * 作者：lengyang 2022/1/13
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class SquareViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    /**
     *  博文数据源
     */
    val squareData: MutableLiveData<List<SquareEntity>> = MutableLiveData()

    /**
     *  获取广场列表
     */
    fun getSquareList() {
        request({ repository.getSquareList(pageNumber.value!!) }, success = {
            squareData.value = it.datas
            loadComplete(success = true, noMore = it.over.toBoolean())
        }, error = {
            loadComplete(false)
        })
    }

    override fun refresh() {
        super.refresh()
        getSquareList()
    }

    override fun loadMore() {
        super.loadMore()
        getSquareList()
    }

    /**
     *  收藏或取消收藏
     */
    fun squareCollect(bean: SquareEntity) {
        bean.id?.let {
            collect(repository, bean.collected, it)
        }
    }

}