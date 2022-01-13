package com.ygg.module_project.ui

import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar
import com.ygg.lib_base.adapter.ViewPagerFmAdapter
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_common.constants.ROUTER_PATH_PROJECT
import com.ygg.lib_common.entity.CategoryEntity
import com.ygg.module_project.R
import com.ygg.module_project.databinding.ProjectFragmentProjectBinding
import com.ygg.module_project.viewmodel.ProjectViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/5
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Route(path = ROUTER_PATH_PROJECT)
class ProjectFragment : BaseFragment<ProjectViewModel, ProjectFragmentProjectBinding>() {
    override val viewModel: ProjectViewModel by viewModel()

    override fun initContentView(): Int = R.layout.project_fragment_project

    override fun initData() {

        viewModel.getProjectCategory()

    }

    override fun initViewObservable() {

        viewModel.apply {
            listData.observe(this@ProjectFragment, {
                initViewPager(it)
            })
        }
    }

    private fun initViewPager(it: List<CategoryEntity>) {
        val fragments = arrayListOf<ContentFragment>()
        val tabTitles = arrayListOf<String>()
        for (data in it) {
            binding.tabLayout.addTab(binding.tabLayout.newTab())
            tabTitles.add(data.name!!)
            fragments.add(ContentFragment.getInstance(data.id.toString()))
        }

        binding.viewpager.apply {
            adapter = ViewPagerFmAdapter(childFragmentManager, lifecycle, fragments)
            // 优化体验设置该属性后第一次将自动加载所有fragment 在子fragment内部添加懒加载机制
            offscreenPageLimit = fragments.size
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}