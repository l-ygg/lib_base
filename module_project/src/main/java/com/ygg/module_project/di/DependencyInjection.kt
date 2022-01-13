package com.ygg.module_project.di

import com.ygg.module_project.viewmodel.ContentViewModel
import com.ygg.module_project.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
val projectViewModelModule: Module = module {
    viewModel { ProjectViewModel(get()) }
    viewModel { ContentViewModel(get()) }
}