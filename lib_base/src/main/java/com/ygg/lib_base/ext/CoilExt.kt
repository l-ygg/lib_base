package com.ygg.lib_base.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import coil.Coil
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
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

/**
 * 从图片地址获取Bitmap
 * @receiver Context
 * @param url String
 * @return Bitmap?
 */
suspend fun Context.getImageBitmapByUrl(url: String): Bitmap? {
    val request = ImageRequest.Builder(this)
        .data(url)
        .allowHardware(false)
        .build()
    val result = (imageLoader.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}