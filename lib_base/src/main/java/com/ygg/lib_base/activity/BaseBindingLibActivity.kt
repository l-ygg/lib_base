package com.ygg.lib_base.activity

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ygg.lib_base.BR
import com.ygg.lib_base.viewmodel.BaseLibViewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseBindingLibActivity<VM : BaseLibViewModel, DB : ViewDataBinding> :
    BaseMvvmLibActivity<VM>() {

    /** DataBinding 对象 */
    protected lateinit var mBinding: DB

    override fun setContentView(layoutResID: Int) {
        // 初始化 DataBinding
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )

        // 绑定生命周期管理
        mBinding.lifecycleOwner = this

        // 绑定 ViewModel
        mBinding.setVariable(BR.viewModel, viewModel)

        // 设置布局
        super.setContentView(mBinding.root)
    }

}