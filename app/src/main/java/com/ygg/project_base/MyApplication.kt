package com.ygg.project_base

import androidx.core.content.ContextCompat
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.ygg.lib_base.base.BaseApplication
import com.ygg.lib_base.net.RetrofitClient
import com.ygg.lib_common.di.netModule
import com.ygg.lib_common.di.repositoryModule
import com.ygg.lib_common.net.UrlDefinition
import com.ygg.module_login.di.loginViewModelModule
import com.ygg.module_main.di.mainViewModelModule
import com.ygg.module_project.di.projectViewModelModule
import com.ygg.module_square.di.squareViewModelModule
import com.ygg.project_base.di.baseViewModelModule
import me.jessyan.autosize.AutoSizeConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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
                    mainViewModelModule,
                    squareViewModelModule,
                    projectViewModelModule
                )
            )
        }

        // 屏幕适配
        AutoSizeConfig.getInstance().setCustomFragment(true).setBaseOnWidth(false)
            .setExcludeFontScale(true).designHeightInDp = 720


    }

    companion object {
        init {
            ClassicsFooter.REFRESH_FOOTER_FINISH = ""
            SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
                layout.apply {
                    setEnableOverScrollDrag(true)
                    setEnableScrollContentWhenLoaded(false)
                    setEnableAutoLoadMore(true)
                    setEnableOverScrollBounce(true)
                    setFooterHeight(60f)
                }
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.apply {
                    setPrimaryColorsId(R.color.md_theme_red, R.color.white)
                }
                MaterialHeader(context).setColorSchemeColors(
                    ContextCompat.getColor(
                        context,
                        R.color.md_theme_red
                    )
                )
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(context).setFinishDuration(0)
            }
        }
    }
}