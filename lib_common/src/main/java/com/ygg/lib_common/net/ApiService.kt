package com.ygg.lib_common.net

import com.ygg.lib_common.entity.*
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

    /** 根据页码[pageNum]获取并返回首页文章列表 */
    @GET(UrlDefinition.GET_HOMEPAGE_ARTICLE_LIST_PROJECT)
    suspend fun getHomepageArticleProjectList(@Path("page") pageNum: Int): BaseBean<ProjectListEntity>

    /** 根据页码[pageNum]获取并返回首页文章列表 */
    @GET(UrlDefinition.GET_SQUARE_ARTICLE_LIST)
    suspend fun getSquareList(@Path("page") pageNum: Int): BaseBean<SquareListEntity>

    /** 通过文章[id]收藏站内文章 */
    @POST(UrlDefinition.COLLECT_ARTICLE_INSIDE)
    suspend fun collectArticleInside(@Path("id") id: String): BaseBean<Any>

    /** 文章列表中根据文章[id]取消收藏 */
    @POST(UrlDefinition.UN_COLLECT_ARTICLE_LIST)
    suspend fun unCollectArticleList(@Path("id") id: String): BaseBean<Any>

    /** 获取并返回项目分类列表 */
    @GET(UrlDefinition.GET_PROJECT_CATEGORY)
    suspend fun getProjectCategory(): BaseBean<ArrayList<CategoryEntity>>

    /** 根据分类id[categoryId]、页码[pageNum]获取并返回项目列表 */
    @GET(UrlDefinition.GET_PROJECT_LIST)
    suspend fun getProjectList(
        @Path("pageNum") pageNum: Int,
        @Query("cid") categoryId: String
    ): BaseBean<ArticleListEntity>
}