package com.ygg.lib_base.databinding.adapter

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.ygg.lib_base.databinding.IMG_DRAWABLE_MARK
import com.ygg.lib_base.databinding.IMG_MIPMAP_MARK
import com.ygg.lib_base.databinding.getIdentifier
import com.ygg.lib_base.ext.loadImage

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：ImageView DataBinding 适配器
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */


/** 根据资源id [resId] 给 [iv] 加载图片 */
@BindingAdapter("android:bind_src")
fun src(iv: ImageView, @DrawableRes resId: Int?) {
    if (null != resId && 0 != resId) {
        iv.setImageResource(resId)
    }
}

/**
 * 根据资源字符串 [res] 给 [iv] 加载图片
 * > [res]: [IMG_DRAWABLE_MARK]:resId or [IMG_MIPMAP_MARK]:resId
 */
@BindingAdapter("android:bind_src")
fun setImageResource(iv: ImageView, res: String?) {
    if (null != res && res.isNotBlank()) {
        if (res.startsWith(IMG_DRAWABLE_MARK)) {
            val realRes = res.split(":")[1]
            iv.setImageResource(realRes.getIdentifier(iv.context, "drawable"))
        } else if (res.startsWith(IMG_MIPMAP_MARK)) {
            val realRes = res.split(":")[1]
            iv.setImageResource(realRes.getIdentifier(iv.context, "mipmap"))
        }
    }
}

/**
 * 根据资源字符串 [res] 给 [iv] 加载图片
 * > [res]: [IMG_DRAWABLE_MARK]:resId or [IMG_MIPMAP_MARK]:resId
 */
@BindingAdapter("android:bind_url")
fun setImageUrl(iv: ImageView, url: String?) {
    if (null != url && url.isNotBlank()) {
        iv.loadImage(url)
    }
}