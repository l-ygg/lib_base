package com.ygg.lib_base.viewmodel

import androidx.lifecycle.ViewModel
import com.ygg.lib_base.log.InternalLog

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：MVVM ViewModel 基类
 * <p>- 继承 [ViewModel]
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseLibViewModel: ViewModel() {

    override fun onCleared() {
        InternalLog.i("BaseLibViewModel", "View onCleared ----> ViewModel: $this")
    }

}