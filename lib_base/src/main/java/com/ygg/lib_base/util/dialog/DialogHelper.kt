package com.ygg.lib_base.util.dialog

import android.content.Context
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.ygg.lib_base.R

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


fun showLoadingDialog(context: Context, title: String?="加载中"): BasePopupView {
    return XPopup.Builder(context).asLoading(title, R.layout.base_loading_dialog).show()
}