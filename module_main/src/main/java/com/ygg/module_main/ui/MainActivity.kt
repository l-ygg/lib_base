package com.ygg.module_main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ygg.lib_base.adapter.ViewPagerFmAdapter
import com.ygg.lib_base.base.BaseActivity
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_common.constants.*
import com.ygg.module_main.R
import com.ygg.module_main.viewmodel.MainViewModel
import com.ygg.module_main.databinding.MainActivityMainBinding
import me.yokeyword.fragmentation.SupportFragment
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
        initViewPager()

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

    /**
     *  初始化viewpager
     */
    private fun initViewPager() {
        val homeFragment = RouteCenter.navigate(ROUTER_PATH_HOME) as SupportFragment
        val squareFragment =
            RouteCenter.navigate(ROUTER_PATH_SQUARE) as SupportFragment
        val projectFragment =
            RouteCenter.navigate(ROUTER_PATH_PROJECT) as SupportFragment
        val userFragment = RouteCenter.navigate(ROUTER_PATH_MINE) as SupportFragment
        val fragments = arrayListOf(homeFragment, squareFragment, projectFragment, userFragment)
        binding.viewPager2.apply {
            adapter = ViewPagerFmAdapter(supportFragmentManager, lifecycle, fragments)
            offscreenPageLimit = fragments.size
        }
    }
}