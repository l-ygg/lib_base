package com.ygg.lib_base.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.ygg.lib_base.R
import com.ygg.lib_base.activity.BaseBindingLibActivity
import com.ygg.lib_base.rotue.RouteCenter
import com.ygg.lib_base.viewmodel.BaseLibViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
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
        return false
    }


    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        if (useBaseLayout()) {

        } else {
            setContentView(initContentView())
        }
    }

    /**
     * 添加观察者
     *  处理界面公共事件
     */
    private fun observeData() {

        // 关闭页面
        viewModel.uiCloseData.observe(this, {
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
    }
}