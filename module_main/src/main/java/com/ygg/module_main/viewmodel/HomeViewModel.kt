package com.ygg.module_main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.net.request
import com.ygg.lib_common.entity.BannerEntity
import com.ygg.lib_common.repository.ArticleRepository
import com.ygg.lib_common.repository.UserRepository

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
class HomeViewModel(private val repository: ArticleRepository) : BaseViewModel() {

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

}