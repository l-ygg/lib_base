package com.ygg.lib_base.ext

import android.widget.ImageView
import coil.Coil
import coil.load
import com.ygg.lib_base.R

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/6
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
fun ImageView.loadImage(url: String?) {
    url?.let {
        this.load(url) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
        }
    }
}