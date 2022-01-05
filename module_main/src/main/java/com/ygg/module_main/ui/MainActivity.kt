package com.ygg.module_main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_common.constants.ROUTER_PATH_MAIN
import com.ygg.module_main.R
import com.ygg.module_main.viewmodel.MainViewModel
import com.ygg.module_main.databinding.MainActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/4
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Route(path = ROUTER_PATH_MAIN)
class MainActivity : BaseActivity<MainViewModel, MainActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()
    override fun initContentView(): Int = R.layout.main_activity_main

    override fun initData() {

        initBottomBar()

    }

    override fun initViewObservable() {
    }

    override fun useBaseLayout(): Boolean {
        return false
    }

    /**
     *  初始化底部导航栏
     */
    private fun initBottomBar() {
        binding.bottomBar.apply {
            setMode(BottomNavigationBar.MODE_FIXED)
            setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_home_on,
                    "首页"
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_home_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_square_on,
                    "广场"
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_square_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_project_on,
                    "项目"
                )
                    .setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_project_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_me_on,
                    "我的"
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_me_off)
            )
            setFirstSelectedPosition(0)
            initialise()
        }
    }
}