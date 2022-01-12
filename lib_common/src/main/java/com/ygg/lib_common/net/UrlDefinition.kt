package com.ygg.lib_common.net

import com.ygg.lib_common.BuildConfig

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
object UrlDefinition {

    /** 正式环境  */
    private const val API_ONLINE = "https://www.wanandroid.com"
    /** 测试环境  */
    private const val API_OFFLINE = "https://www.wanandroid.com"

    /** 服务器url  */
    @Suppress("ConstantConditionIf")
    val BASE_URL = (if (BuildConfig.DEBUG) API_OFFLINE else API_ONLINE)

    /** 获取首页 Banner 列表 */
    const val GET_HOMEPAGE_BANNER_LIST = "/banner/json"
    /** 获取首页置顶文章列表 */
    const val GET_HOMEPAGE_TOP_ARTICLE_LIST = "/article/top/json"
    /** 获取首页文章列表 */
    const val GET_HOMEPAGE_ARTICLE_LIST = "/article/list/{pageNum}/json"
    /** 获取首页热门项目列表 */
    const val GET_HOMEPAGE_ARTICLE_LIST_PROJECT = "/article/listproject/{page}/json"

    /** 获取收藏列表 */
    const val GET_COLLECTION_LIST = "/lg/collect/list/{pageNum}/json"
    /** 收藏站内文章 */
    const val COLLECT_ARTICLE_INSIDE = "/lg/collect/{id}/json"
    /** 收藏站内文章 */
    const val COLLECT_ARTICLE_OUTSIDE = "/lg/collect/add/json"
    /** 取消收藏 - 文章列表 */
    const val UN_COLLECT_ARTICLE_LIST = "/lg/uncollect_originId/{id}/json"
    /** 取消收藏 - 我的收藏 */
    const val UN_COLLECT_ARTICLE_COLLECTED = "/lg/uncollect/{id}/json"
    /** 获取收藏网站列表 */
    const val GET_COLLECTED_WEB_LIST = "/lg/collect/usertools/json"
    /** 删除收藏网站 */
    const val DELETE_COLLECTED_WEB = "/lg/collect/deletetool/json"
    /** 收藏网站 */
    const val COLLECT_WEB = "/lg/collect/addtool/json"
    /** 编辑网站 */
    const val EDIT_COLLECTED_WEB = "/lg/collect/updatetool/json"

    /** 获取体系分类列表 */
    const val GET_SYSTEM_CATEGORY_LIST = "/tree/json"
    /** 获取导航列表 */
    const val GET_NAVIGATION_LIST = "/navi/json"
    /** 获取体系文章列表 */
    const val GET_SYSTEM_ARTICLE_LIST = "/article/list/{pageNum}/json"

    /** 获取公众号列表 */
    const val GET_BJNEWS_LIST = "/wxarticle/chapters/json"
    /** 获取公众号文章列表 */
    const val GET_BJNEWS_ARTICLES = "/wxarticle/list/{id}/{pageNum}/json"

    /** 获取项目分类列表 */
    const val GET_PROJECT_CATEGORY = "/project/tree/json"

    /** 获取项目列表 */
    const val GET_PROJECT_LIST = "/project/list/{pageNum}/json"

    /** 登录 */
    const val LOGIN = "/user/login"

    /** 注册 */
    const val REGISTER = "/user/register"

    /** 退出登录 */
    const val LOGOUT = "/user/logout/json"

    /** 个人积分 */
    const val USER_COINS = "/lg/coin/userinfo/json"

    /** 个人积分列表 */
    const val USER_COINS_LIST = "/lg/coin/list/{pageNum}/json"

    /** 积分排行榜 */
    const val COINS_RANKING = "/coin/rank/{pageNum}/json"

    /** 获取搜索热词 */
    const val GET_HOT_SEARCH = "/hotkey/json"

    /** 搜索 */
    const val SEARCH = "/article/query/{pageNum}/json"

    /** 问答列表 */
    const val QA_LIST = "/wenda/list/{pageNum}/json"
}