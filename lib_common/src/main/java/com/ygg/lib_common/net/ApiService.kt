package com.ygg.lib_common.net

import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.entity.ArticleListEntity
import com.ygg.lib_common.entity.BannerEntity
import com.ygg.lib_common.entity.UserInfoEntity
import retrofit2.http.*


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

    /** 通过用户名[username]、密码[password]注册用户并返回用户信息 */
    @FormUrlEncoded
    @POST(UrlDefinition.REGISTER)
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String = password
    ): BaseBean<UserInfoEntity>

    /** 获取并返回首页 Banner 列表 */
    @GET(UrlDefinition.GET_HOMEPAGE_BANNER_LIST)
    suspend fun getHomepageBannerList(): BaseBean<List<BannerEntity>>

    /** 获取并返回首页置顶文章列表 */
    @GET(UrlDefinition.GET_HOMEPAGE_TOP_ARTICLE_LIST)
    suspend fun getHomepageTopArticleList(): BaseBean<ArrayList<ArticleEntity>>

    /** 根据页码[pageNum]获取并返回首页文章列表 */
    @GET(UrlDefinition.GET_HOMEPAGE_ARTICLE_LIST)
    suspend fun getHomepageArticleList(@Path("pageNum") pageNum: Int): BaseBean<ArticleListEntity>

    /** 通过文章[id]收藏站内文章 */
    @POST(UrlDefinition.COLLECT_ARTICLE_INSIDE)
    suspend fun collectArticleInside(@Path("id") id: String): BaseBean<Any>

    /** 文章列表中根据文章[id]取消收藏 */
    @POST(UrlDefinition.UN_COLLECT_ARTICLE_LIST)
    suspend fun unCollectArticleList(@Path("id") id: String): BaseBean<Any>
}