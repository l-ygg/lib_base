package com.ygg.lib_common.entity

import kotlinx.serialization.Serializable

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/31
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Serializable
data class UserInfoEntity(
    val email: String? = "",
    val icon: String? = "",
    val id: String? = "",
    val nickname: String? = "",
    val password: String? = "",
    val publicName: String? = "",
    val token: String? = "",
    val username: String? = ""
)