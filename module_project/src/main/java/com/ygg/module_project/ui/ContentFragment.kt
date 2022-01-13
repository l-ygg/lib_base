package com.ygg.module_project.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.module_project.R
import com.ygg.module_project.adapter.ProjectGridAdapter
import com.ygg.module_project.databinding.ProjectFragmentContentBinding
import com.ygg.module_project.viewmodel.ContentViewModel
import com.ygg.module_project.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/13
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class ContentFragment : BaseFragment<ContentViewModel, ProjectFragmentContentBinding>() {

    private lateinit var mAdapter: ProjectGridAdapter
    private var firstLoad = true

    companion object {
        const val SORT_ID = "sort_id"
        fun getInstance(id: String): ContentFragment = ContentFragment().apply {
            arguments = Bundle().apply {
                putString(SORT_ID, id)
            }
        }
    }

    public override val viewModel: ContentViewModel by viewModel()
    override fun initContentView(): Int = R.layout.project_fragment_content

    override fun initData() {

        initAdapter()

        viewModel.categoryId = arguments?.getString(SORT_ID).toString()


    }

    override fun onResume() {
        super.onResume()
        // 懒加载
        if (firstLoad) {
            // 仅加载第一个fragment的数据缓存
            if (parentFragment is ProjectFragment && (parentFragment as ProjectFragment).binding.viewpager.currentItem == 0) {
                viewModel.getProjectList()
                return
            }
            binding.smartCommon.autoRefresh()
        }
    }

    override fun initViewObservable() {

        viewModel.apply {
            listData.observe(this@ContentFragment, {
                if (viewModel.pageNumber.value == NET_PAGE_START) {
                    mAdapter.setDiffNewData(it)
                } else {
                    mAdapter.addData(it)
                }
                firstLoad=false
            })
        }
    }

    private fun initAdapter() {
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //解决item跳动
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mAdapter = ProjectGridAdapter(this)
        mAdapter.setDiffCallback(mAdapter.diffConfig)
        binding.ryCommon.apply {
            layoutManager = manager
            adapter = mAdapter
            // 禁用动画
            binding.ryCommon.itemAnimator = null
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    // 解决滑到顶部留白问题
                    manager.invalidateSpanAssignments()
                }
            })
        }
    }
}