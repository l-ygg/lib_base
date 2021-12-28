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


/** 若对象为`null`，返回[empty]，否则返回对象本身 */
fun <T> T?.orElse(empty: T): T {
    return this ?: empty
}

/** 优化[toString]，对象为`null`时返回`"null"` */
fun Any?.toSafeString(): String {
    return this?.toString() ?: "null"
}