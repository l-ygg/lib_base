package com.ygg.lib_base.base

import androidx.lifecycle.MutableLiveData
import com.ygg.lib_base.model.UiCloseModel
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
abstract class BaseViewModel: BaseLibViewModel() {

    /** 控制 UI 组件关闭 */
    val uiCloseData = MutableLiveData<UiCloseModel>()

    /** 界面跳转控制 */
    val uiStartActivity = MutableLiveData<UiStartActModel>()

}