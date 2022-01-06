package com.ygg.lib_base.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.lxj.xpopup.core.BasePopupView
import com.ygg.lib_base.BR
import com.ygg.lib_base.R
import com.ygg.lib_base.fragment.BaseBindingLibFragment
import com.ygg.lib_base.mvvm.ContainerFmActivity
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_base.util.dialog.showLoadingDialog
import me.yokeyword.fragmentation.SupportFragment

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
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>
    : BaseBindingLibFragment<VM, DB>() {

    val TAG: String = javaClass.simpleName
    private lateinit var rootView: View
    protected var rootBinding: ViewDataBinding? = null
    private var dialog: BasePopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (useBaseLayout()) {
            rootView = inflater.inflate(R.layout.base_activity_base, null, false)
                .findViewById(R.id.ll_root)
            rootBinding = DataBindingUtil.bind(rootView)
            binding =
                DataBindingUtil.inflate(inflater, initContentView(), rootView as ViewGroup, true)
            rootView
        } else {
            binding = DataBindingUtil.inflate(inflater, initContentView(), container, false)
            binding.root
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        //私有的初始化DataBinding和ViewModel方法
        initViewDataBinding(savedInstanceState)
        //私有的ViewModel与View的契约事件回调逻辑
        observeData()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        initImmersionbar()
    }

    /**
     * 正常创建启动Fragment情况 onViewCreated-onLazyInitView-onEnterAnimationEnd
     * Viewpager创建实例 onViewCreated-onLazyInitView
     */
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        if (enableLazy()) {
            //页面数据初始化方法
            initData()
            //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
            initViewObservable()
        }
    }

    /**
     * 入栈动画完毕后执行
     */
    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        if (!enableLazy()) {
            //页面数据初始化方法
            initData()
            //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
            initViewObservable()
        }
    }

    /**
     * 是否开启懒加载,默认true
     *
     * @return
     */
    protected open fun enableLazy(): Boolean {
        return true
    }

    /**
     * 统一处理回退事件
     */
    open fun back() {
        if (preFragment == null) {
            requireActivity().finish()
        } else {
            pop()
        }
    }

    /** 初始化状态栏相关配置 */
    protected open fun initImmersionbar(immersionBar: ImmersionBar) {
    }

    /** 初始化状态栏相关配置 */
    private fun initImmersionbar() {
        immersionBar {
            statusBarColor(R.color.app_immersion_bar)
            statusBarDarkFont(true,0.2f)
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
        return false
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
        rootBinding?.setVariable(BR.viewModel, viewModel)
        rootBinding?.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.lifecycleOwner = this
    }

    /**
     * 添加观察者
     *  处理界面公共事件
     */
    private fun observeData() {

        // 关闭页面
        viewModel.uiCloseActivity.observe(viewLifecycleOwner, {
            back()
        })

        // 跳转页面
        viewModel.uiStartActivity.observe(viewLifecycleOwner, {
            it?.let {
                RouteCenter.navigate(it.path, it.bundle)
            }
        })

        // 加载弹窗
        viewModel.uiLoadingDialog.observe(viewLifecycleOwner, {
            if (it.isShow) {
                showLoading(it.title)
            } else {
                dismissLoading()
            }
        })

        // 跳转fragment容器界面
        viewModel.uiStartContainerActivity.observe(viewLifecycleOwner, {
            startContainerActivity(it.path, it.bundle)
        })

        // 跳转fragment界面
        viewModel.uiStartFragment.observe(viewLifecycleOwner, {
            startFragment(it.path, it.bundle)
        })
    }

    open fun showLoading(title: String?) {
        dialog = showLoadingDialog(requireContext(), title)
    }

    open fun dismissLoading() {
        dialog?.smartDismiss()
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    open fun startActivity(clz: Class<*>?) {
        startActivity(Intent(requireContext(), clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(requireContext(), clz)
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
        val intent = Intent(requireContext(), ContainerFmActivity::class.java)
        intent.putExtra(ContainerFmActivity.FRAGMENT, routePath)
        bundle?.let {
            intent.putExtra(ContainerFmActivity.BUNDLE, it)
        }
        startActivity(intent)
    }

    /**
     *  跳转到fragment界面
     */
    open fun startFragment(
        routePath: String,
        bundle: Bundle? = null
    ) {
        start(RouteCenter.navigate(routePath, bundle) as SupportFragment)
    }
}