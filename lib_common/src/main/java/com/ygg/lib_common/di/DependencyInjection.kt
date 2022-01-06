package com.ygg.lib_common.di

import com.ygg.lib_base.net.RetrofitClient
import com.ygg.lib_common.net.ApiService
import com.ygg.lib_common.repository.ArticleRepository
import com.ygg.lib_common.repository.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */

/** ViewModel Module */
val netModule: Module = module {
    single {
        RetrofitClient.getInstance().addHttpLog(true)
        RetrofitClient.getInstance().init()
        RetrofitClient.getInstance().create(ApiService::class.java)
    }
}

/** 数据仓库 Module */
val repositoryModule: Module = module {
    factory { UserRepository(get()) }
    factory { ArticleRepository(get()) }
}