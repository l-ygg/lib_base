package com.ygg.lib_base.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.lxj.xpopup.core.BasePopupView
import com.ygg.lib_base.BR
import com.ygg.lib_base.R
import com.ygg.lib_base.activity.BaseBindingLibActivity
import com.ygg.lib_base.mvvm.ContainerFmActivity
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_base.util.dialog.showLoadingDialog
import com.ygg.lib_base.viewmodel.BaseLibViewModel
import me.yokeyword.fragmentation.SupportFragment

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：Activity 基类
 * > 指定 ViewModel 类型 [VM] & 指定 DataBinding 类型 [DB]
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseBindingLibActivity<VM, DB>() {

    val TAG: String = javaClass.simpleName
    private var rootBinding: ViewDataBinding? = null
    private var dialog: BasePopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)

        //页面接受的参数方法
        initParam()

        //初始化根布局
        initViewDataBinding(savedInstanceState)

        // 初始化状态栏工具
        initImmersionbar()
        // 添加观察者
        observeData()

        //页面数据初始化方法
        initData()

        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
    }

    /** 初始化状态栏相关配置 */
    protected open fun initImmersionbar(immersionBar: ImmersionBar) {
    }

    /** 初始化状态栏相关配置 */
    private fun initImmersionbar() {
        immersionBar {
            statusBarColor(R.color.app_immersion_bar)
            statusBarDarkFont(false)
            fitsSystemWindows(true)
            initImmersionbar(this)
            addTag(TAG)
        }
    }

    /**
     *  页面接收参数方法
     */
    open fun initParam() {}

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int


    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initData()

    /**
     * 页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
     *
     * @return 布局layout的id
     */
    abstract fun initViewObservable()

    /**
     * @return 是否需要标题栏
     */
    protected open fun useBaseLayout(): Boolean {
        return true
    }

    /**
     * 添加根内容布局id（目前在xml内加了标题栏）
     *
     * @return
     */
    protected open fun addParentContentView(): Int {
        return 0
    }

    /** [onCreate] 之前执行，可用于配置动画 */
    protected open fun beforeOnCreate() {
    }


    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        if (useBaseLayout()) {
            setContentView(R.layout.base_activity_base)
            val mActivityRoot = findViewById<ViewGroup>(R.id.ll_root)
            var parentContent: View = mActivityRoot
            // 绑定根布局
            rootBinding = DataBindingUtil.bind(parentContent)
            rootBinding?.setVariable(BR.viewModel, viewModel)
            rootBinding?.lifecycleOwner = this
            // 在根布局添加公共布局 目前只添加了标题栏
            if (addParentContentView() != 0) {
                parentContent = LayoutInflater.from(this).inflate(addParentContentView(), null)
                mActivityRoot.addView(parentContent)
            }
            binding = DataBindingUtil.inflate(
                layoutInflater,
                initContentView(),
                parentContent as ViewGroup,
                true
            )
        } else {
            // 初始化 DataBinding
            binding = DataBindingUtil.setContentView(this, initContentView())
        }
        // 绑定生命周期管理
        binding.lifecycleOwner = this

        // 绑定 ViewModel
        binding.setVariable(BR.viewModel, viewModel)
    }

    /**
     * 添加观察者
     *  处理界面公共事件
     */
    private fun observeData() {

        // 关闭页面
        viewModel.uiCloseActivity.observe(this, {
            it?.let {
                setResult(it.resultCode, it.result)
            }
            finish()
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

        // 跳转fragment容器界面
        viewModel.uiStartContainerActivity.observe(this, {
            startContainerActivity(it.path, it.bundle)
        })

        // 跳转fragment界面
        viewModel.uiStartFragment.observe(this, {
            startFragment(it.path, it.bundle)
        })
    }

    open fun showLoading(title: String?) {
        dialog = showLoadingDialog(this, title)
    }

    open fun dismissLoading() {
        dialog?.smartDismiss()
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳转容器页面
     * @param routePath Fragment路由地址
     * @param bundle    跳转所携带的信息
     */
    open fun startContainerActivity(
        routePath: String?,
        bundle: Bundle? = null
    ) {
        val intent = Intent(this, ContainerFmActivity::class.java)
        intent.putExtra(ContainerFmActivity.FRAGMENT, routePath)
        bundle?.let {
            intent.putExtra(ContainerFmActivity.BUNDLE, it)
        }
        startActivity(intent)
    }

    /**
     *  跳转到fragment界面
     */
    fun startFragment(
        routePath: String,
        bundle: Bundle? = null
    ) {
        start(RouteCenter.navigate(routePath, bundle) as SupportFragment)
    }
}