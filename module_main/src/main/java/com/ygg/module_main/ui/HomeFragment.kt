package com.ygg.module_main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.gyf.immersionbar.ImmersionBar
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.ROUTER_PATH_HOME
import com.ygg.module_main.R
import com.ygg.module_main.adapter.MyBannerAdapter
import com.ygg.module_main.databinding.MainFragmentHomeBinding
import com.ygg.module_main.viewmodel.HomeViewModel
import com.youth.banner.transformer.AlphaPageTransformer
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
    private lateinit var bannerAdapter: MyBannerAdapter

    override val viewModel: HomeViewModel by viewModel()

    override fun initContentView(): Int = R.layout.main_fragment_home

    override fun initImmersionbar(immersionBar: ImmersionBar) {
        immersionBar.fitsSystemWindows(true)
        super.initImmersionbar(immersionBar)
    }

    override fun initData() {
        initBanner()

        viewModel.getBannerList()
    }

    override fun initViewObservable() {

        viewModel.bannerData.observe(this, {
            bannerSkeleton.hide()
            bannerAdapter = MyBannerAdapter(it, this)
            binding.banner.adapter = bannerAdapter
        })
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
}