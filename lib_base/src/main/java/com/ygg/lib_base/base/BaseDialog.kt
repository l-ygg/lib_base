package com.ygg.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.lxj.xpopup.core.BasePopupView
import com.ygg.lib_base.dialog.BaseBindingLibDialog
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_base.util.dialog.showLoadingDialog

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/30
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseDialog<VM : BaseViewModel, DB : ViewDataBinding>
    : BaseBindingLibDialog<VM, DB>() {

    private var loadingDialog: BasePopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 添加观察者
        observeData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val createView = super.onCreateView(inflater, container, savedInstanceState)
        initImmersionBar()
        return createView
    }

    /** 初始化状态栏相关 */
    protected open fun initImmersionBar(immersionBar: ImmersionBar) {
    }

    /** 初始化状态栏相关 */
    private fun initImmersionBar() {
        immersionBar {
            // 同步所在 Activity 状态栏
            getTag(mContext.javaClass.simpleName)
            initImmersionBar(this)
        }
    }

    /**
     * 添加观察者
     */
    private fun observeData() {
        // 关闭页面
        viewModel.uiCloseActivity.observe(this, {
            dismiss()
        })

        // 跳转页面
        viewModel.uiStartActivity.observe(this, {
            it?.let {
                RouteCenter.navigate(it.path, it.bundle)
            }
        })

        // 加载弹窗
        viewModel.uiLoadingDialog.observe(this, {
            if (it.isShow) {
                showLoading(it.title)
            } else {
                dismissLoading()
            }
        })
    }

    /** 使用 [activity] 显示弹窗 */
    fun show(activity: FragmentActivity) {
        show(activity.supportFragmentManager, javaClass.simpleName)
    }

    /** 使用 [fragment] 显示弹窗 */
    fun show(fragment: Fragment) {
        show(fragment.childFragmentManager, javaClass.simpleName)
    }

    open fun showLoading(title: String?) {
        loadingDialog = showLoadingDialog(mContext, title)
    }

    open fun dismissLoading() {
        loadingDialog?.smartDismiss()
    }

}