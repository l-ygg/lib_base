package com.ygg.project_base

import android.annotation.SuppressLint
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.ygg.lib_base.base.BaseApplication
import com.ygg.lib_base.log.InternalLog
import com.ygg.module_login.di.loginViewModelModule
import com.ygg.project_base.di.baseViewModelModule
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

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()

        startKoin {
            Log.e("TAG","初始化")
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(loginViewModelModule, baseViewModelModule))
        }
    }

}