package com.ygg.lib_base.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.ygg.lib_base.BuildConfig
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
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 初始化 ARouter
        if (BuildConfig.DEBUG) {
            // Debug 模式或非线上模式
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}