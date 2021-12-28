package com.ygg.lib_base.ext

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/28
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */


/** [Boolean]为`null`则默认`false` */
fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

/** [Boolean]为`null`则默认`true` */
fun Boolean?.orTrue(): Boolean {
    return this ?: true
}

/** 仅为`true`时为`true` */
val Boolean?.condition: Boolean
    get() = this == true