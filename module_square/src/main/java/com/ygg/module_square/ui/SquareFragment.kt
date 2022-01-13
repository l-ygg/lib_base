package com.ygg.module_square.ui

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.NET_PAGE_START
import com.ygg.lib_common.constants.ROUTER_PATH_SQUARE
import com.ygg.lib_common.entity.SquareEntity
import com.ygg.module_square.R
import com.ygg.module_square.adapter.SquareAdapter
import com.ygg.module_square.databinding.SquareFragmentSquareBinding
import com.ygg.module_square.viewmodel.SquareViewModel
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
@Route(path = ROUTER_PATH_SQUARE)
class SquareFragment : BaseFragment<SquareViewModel, SquareFragmentSquareBinding>() {

    lateinit var squareAdapter: SquareAdapter

    override val viewModel: SquareViewModel by viewModel()

    override fun initContentView(): Int = R.layout.square_fragment_square

    override fun onSupportVisible() {
        lifecycleScope.launch {
            // 延时 1000ms
            delay(10)

            ImmersionBar.with(requireActivity()).fitsSystemWindows(true)
                .statusBarDarkFont(true).init()
        }
    }


    override fun initData() {

        initRv()

        viewModel.getSquareList()
    }

    override fun initViewObservable() {

        viewModel.apply {
            squareData.observe(this@SquareFragment, {
                if (pageNumber.value == NET_PAGE_START) {
                    squareAdapter.setDiffNewData(it as MutableList<SquareEntity>?)
                } else {
                    squareAdapter.addData(it)
                }
            })
        }

    }

    private fun initRv() {
        squareAdapter = SquareAdapter(viewModel)
        squareAdapter.setDiffCallback(squareAdapter.diffConfig)
        binding.ryCommon.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = squareAdapter
        }
    }
}