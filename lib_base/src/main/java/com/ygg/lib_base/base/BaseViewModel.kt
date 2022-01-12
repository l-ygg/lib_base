package com.ygg.lib_base.base

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.R
import com.ygg.lib_base.databinding.adapter.SmartRefreshState
import com.ygg.lib_base.ext.orElse
import com.ygg.lib_base.model.UiCloseModel
import com.ygg.lib_base.model.UiLoadingDialogModel
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_base.viewmodel.BaseLibViewModel

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：* ViewModel 基类
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseViewModel : BaseLibViewModel() {

    /** 控制 UI 组件关闭 */
    val uiCloseActivity = MutableLiveData<UiCloseModel>()

    /** 界面跳转控制 */
    val uiStartActivity = MutableLiveData<UiStartActModel>()

    /** fragment界面跳转控制 */
    val uiStartContainerActivity = MutableLiveData<UiStartActModel>()

    /** 跳转fragment界面 */
    val uiStartFragment = MutableLiveData<UiStartActModel>()

    /**
     *  加载弹窗
     */
    val uiLoadingDialog = MutableLiveData<UiLoadingDialogModel>()

    /** 标题 */
    open val toolbarTitle: ObservableField<String> = ObservableField()

    /**
     *  返回键图片
     */
    open val toolbarBackImgRes: ObservableField<Int> = ObservableField(R.drawable.ic_back_black)

    /**
     *  标题右侧图片
     */
    open val toolbarRightImgRes: ObservableField<Int> = ObservableField()

    /**
     *  标题右侧文字
     */
    open val toolbarRightText: ObservableField<String> = ObservableField()

    val pageNumber: MutableLiveData<Int> = MutableLiveData(0)

    val refreshing: MutableLiveData<SmartRefreshState> = MutableLiveData()

    val loadMore: MutableLiveData<SmartRefreshState> = MutableLiveData()

    val onRefresh: () -> Unit = {
        pageNumber.value = 0
        refresh()
    }

    val onLoadMore: () -> Unit = {
        pageNumber.value = pageNumber.value.orElse(0) + 1
        loadMore()
    }

    /**
     *  子类重写该方法实现操作
     */
    open fun refresh() {}

    /**
     *  子类重写该方法实现操作
     */
    open fun loadMore() {}

    /**
     *  返回键点击
     */
    val onBackClick: () -> Unit = {
        uiCloseActivity.value = UiCloseModel()
        setToolbarBackClick()
    }

    /**
     *  子类重写该方法实现操作
     */
    open fun setToolbarBackClick() {}

    /**
     *  右侧图片点击
     */
    val onRightImgClick: () -> Unit = {
        if (toolbarRightImgRes.get() != 0) {
            setToolbarRightImgClick()
        }
    }


    /**
     *  子类重写该方法实现操作
     */
    open fun setToolbarRightImgClick() {}

    /**
     *  右侧文字点击
     */
    val onRightTextClick: () -> Unit = {
        if (toolbarRightText.get().isNullOrBlank()) {
            setToolbarRightTextClick()
        }
    }

    /**
     *  子类重写该方法实现操作
     */
    open fun setToolbarRightTextClick() {}



}