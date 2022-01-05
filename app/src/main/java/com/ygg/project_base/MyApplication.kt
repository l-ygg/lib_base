package com.ygg.project_base

import android.annotation.SuppressLint
import android.util.Log
import com.ygg.lib_base.base.BaseApplication
import com.ygg.lib_base.log.InternalLog
import com.ygg.lib_base.net.RetrofitClient
import com.ygg.lib_common.di.netModule
import com.ygg.lib_common.di.repositoryModule
import com.ygg.lib_common.net.UrlDefinition
import com.ygg.module_login.di.loginViewModelModule
import com.ygg.module_main.di.mainViewModelModule
import com.ygg.project_base.di.baseViewModelModule
import me.jessyan.autosize.AutoSizeConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        RetrofitClient.baseUrl = UrlDefinition.BASE_URL
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    loginViewModelModule,
                    baseViewModelModule,
                    netModule,
                    repositoryModule,
                    mainViewModelModule
                )
            )
        }

        // 屏幕适配
        AutoSizeConfig.getInstance().setCustomFragment(true).setBaseOnWidth(false)
            .setExcludeFontScale(true).designHeightInDp = 720


    }

}