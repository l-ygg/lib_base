package com.ygg.module_main.ui

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.gyf.immersionbar.ImmersionBar
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.lib_common.constants.ROUTER_PATH_HOME
import com.ygg.lib_common.constants.SPLASH_DELAY_MS
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.ProjectEntity
import com.ygg.module_main.R
import com.ygg.module_main.adapter.HomeArticleAdapter
import com.ygg.module_main.adapter.HomeProjectAdapter
import com.ygg.module_main.adapter.MyBannerAdapter
import com.ygg.module_main.databinding.MainFragmentHomeBinding
import com.ygg.module_main.viewmodel.HomeViewModel
import com.youth.banner.transformer.AlphaPageTransformer
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

@Route(path = ROUTER_PATH_HOME)
class HomeFragment : BaseFragment<HomeViewModel, MainFragmentHomeBinding>() {

    private lateinit var bannerSkeleton: SkeletonScreen
    private lateinit var articleSkeleton: SkeletonScreen
    private lateinit var projectSkeleton: SkeletonScreen
    private lateinit var bannerAdapter: MyBannerAdapter
    lateinit var mArticleAdapter: HomeArticleAdapter
    lateinit var projectAdapter: HomeProjectAdapter

    public override val viewModel: HomeViewModel by viewModel()

    override fun initContentView(): Int = R.layout.main_fragment_home


    override fun onSupportVisible() {
        lifecycleScope.launch {
            // 延时 1000ms
            delay(10)

            ImmersionBar.with(requireActivity()).fitsSystemWindows(true)
                .statusBarDarkFont(true).init()
        }
    }

    override fun initData() {
        initBanner()
        initRv()

        viewModel.getBannerList()
        viewModel.getHomeArticle()
        viewModel.getHomeArticleProject()
    }

    override fun initViewObservable() {

        viewModel.apply {
            bannerData.observe(this@HomeFragment, {
                bannerSkeleton.hide()
                bannerAdapter = MyBannerAdapter(it, this@HomeFragment)
                binding.banner.adapter = bannerAdapter
            })

            articleData.observe(this@HomeFragment, {
                articleSkeleton.hide()
                if (viewModel.pageNumber.value == NET_PAGE_START) {
                    mArticleAdapter.setDiffNewData(it as MutableList<ArticleEntity>?)
                } else {
                    mArticleAdapter.addData(it)
                }
            })

            projectData.observe(this@HomeFragment, {
                projectSkeleton.hide()
                if (viewModel.projectPageNumber.value == NET_PAGE_START) {
                    projectAdapter.setDiffNewData(it as MutableList<ProjectEntity>?)
                } else {
                    projectAdapter.addData(it)
                }
            })
        }
    }

    private fun initBanner() {
        bannerSkeleton = Skeleton.bind(binding.banner)
            .load(R.layout.main_banner_skeleton)
            .show()
        binding.banner.apply {
            addBannerLifecycleObserver(this@HomeFragment)
            setBannerGalleryEffect(18, 10)
            addPageTransformer(AlphaPageTransformer(0.6f))
        }
    }

    private fun initRv() {
        mArticleAdapter = HomeArticleAdapter(viewModel)
        mArticleAdapter.setDiffCallback(mArticleAdapter.diffConfig)
        binding.ryArticle.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mArticleAdapter
        }
        articleSkeleton = Skeleton.bind(binding.ryArticle)
            .adapter(mArticleAdapter)
            .load(R.layout.main_article_item_skeleton)
            .show()

        projectAdapter = HomeProjectAdapter(viewModel)
        projectAdapter.setDiffCallback(projectAdapter.diffConfig)
        binding.ryProject.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = projectAdapter
        }

        projectSkeleton = Skeleton.bind(binding.ryProject)
            .adapter(projectAdapter)
            .load(R.layout.main_item_project_skeleton)
            .show()
    }
}