package com.ygg.lib_base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ygg.lib_base.BR
import com.ygg.lib_base.viewmodel.BaseLibViewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：Fragment基类
 * - 添加了对 DataBinding 的支持
 * - [onCreateView] 方法中处理了对 [mBinding] 的初始化
 *
 * @param VM MVVM ViewModel 类，继承 [BaseLibViewModel]
 * @param DB [ViewDataBinding] 对象
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseBindingLibFragment<VM : BaseLibViewModel, DB : ViewDataBinding>
    : BaseMvvmLibFragment<VM>() {

    /** DataBinding 对象 */
    protected lateinit var mBinding: DB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (null == rootView) {
            // 初始化 DataBinding
            mBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

            // 绑定生命周期管理
            mBinding.lifecycleOwner = this

            // 绑定 ViewModel
            mBinding.setVariable(BR.viewModel, viewModel)
            rootView = mBinding.root

            // 初始化布局
            initView()
        } else {
            (rootView?.parent as? ViewGroup?)?.removeView(rootView)
        }

        return rootView
    }
}