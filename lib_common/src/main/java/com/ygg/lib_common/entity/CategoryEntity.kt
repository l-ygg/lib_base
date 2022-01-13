package com.ygg.lib_common.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/13
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Parcelize
@Serializable
data class CategoryEntity(
    val id: String? = "",
    val name: String? = ""
) : Parcelable