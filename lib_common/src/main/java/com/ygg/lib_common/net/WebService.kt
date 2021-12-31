package com.ygg.lib_common.net

import com.ygg.lib_common.entity.UserInfoEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * 网络请求接口
 */
interface ApiService {

    /** 通过用户名[username]、密码[password]登录并返回用户信息 */
    @FormUrlEncoded
    @POST(UrlDefinition.LOGIN)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<UserInfoEntity>

}