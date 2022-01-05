package com.ygg.module_square

import com.alibaba.android.arouter.facade.annotation.Route
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.viewmodel.BlankViewModel
import com.ygg.lib_common.constants.ROUTER_PATH_SQUARE
import com.ygg.module_square.databinding.SquareFragmentSquareBinding
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
class SquareFragment : BaseFragment<BlankViewModel, SquareFragmentSquareBinding>() {

    override val viewModel: BlankViewModel by viewModel()

    override fun initContentView(): Int = R.layout.square_fragment_square

    override fun initData() {
    }

    override fun initViewObservable() {
    }
}