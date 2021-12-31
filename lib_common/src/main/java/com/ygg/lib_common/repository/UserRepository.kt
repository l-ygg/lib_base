package com.ygg.lib_common.repository

import com.ygg.lib_base.net.netRequest
import com.ygg.lib_common.net.ApiService

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
class UserRepository(private val apiService: ApiService) {

    /** 通过用户名[username]、密码[password]登录并返回用户信息 */
    suspend fun login(username: String, password: String) = netRequest {
        apiService.login(username, password)
    }

}