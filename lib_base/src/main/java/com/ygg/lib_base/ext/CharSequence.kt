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



/** 字符序列是否不为 `null` 且不是空格 */
fun CharSequence?.isNotNullAndBlank(): Boolean {
    return !isNullOrBlank()
}

/** 字符序列是否不为 `null` 且不是空串 */
fun CharSequence?.isNotNullAndEmpty(): Boolean {
    return !isNullOrEmpty()
}

/** 字符串中是否包含 Emoji */
fun CharSequence.containsEmoji(): Boolean {
    return (this.indices)
        .map { this[it].toInt() }
        .none {
            (it == 0x0) || (it == 0x9) || (it == 0xA) || (it == 0xD)
                    || (it in 0x20..0xD7FF)
                    || (it in 0xE000..0xFFFD)
                    || (it in 0x10000..0x10FFFF)
        }
}