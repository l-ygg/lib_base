package com.ygg.lib_base.ext

import android.graphics.Color
import android.text.Html
import android.text.Spanned
import com.ygg.lib_base.log.InternalLog

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


/** 从字符串[str]中解析[Html]返回[Spanned]对象 */
fun parseHtmlFromString(str: String): Spanned? {
    @Suppress("DEPRECATION")
    return Html.fromHtml(str)
}

/** 从字符串[str]中解析并返回颜色值[Int] */
fun parseColorFromString(str: String): Int? {
    return try {
        Color.parseColor(str)
    } catch (e: Exception) {
        InternalLog.e("String.kt", e, "parseColor")
        null
    }
}

/** 从对象[String]以及候选对象[strArray]中按先后顺序获取非空[String]对象，若全部为空返回`""` */
fun String?.orEmpty(vararg strArray: String?): String {
    return this ?: (strArray.firstOrNull {
        null != it
    } ?: "")
}