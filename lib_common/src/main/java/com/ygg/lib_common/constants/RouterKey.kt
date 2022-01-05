package com.ygg.lib_common.constants

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

/** 路由组 - 用户 */
const val ROUTER_GROUP_LOGIN = "/login"

/** 路由组 - 主页 */
const val ROUTER_GROUP_MAIN = "/main"

/** 路由组 - 广场 */
const val ROUTER_GROUP_SQUARE = "/square"

/** 路由组 - 项目 */
const val ROUTER_GROUP_PROJECT = "/project"

/** 路由组 - 用户 */
const val ROUTER_GROUP_USER = "/user"

/** 登录页面 [LoginActivity] */
const val ROUTER_PATH_LOGIN = "$ROUTER_GROUP_LOGIN/login"

/** 注册页面 [RegisterActivity] */
const val ROUTER_PATH_REGISTER = "$ROUTER_GROUP_LOGIN/register"

/** 主页 [MainActivity] */
const val ROUTER_PATH_MAIN = "$ROUTER_GROUP_MAIN/main"

/** 首页 [HomeFragment] */
const val ROUTER_PATH_HOME = "$ROUTER_GROUP_MAIN/home"

/** 广场 [SquareFragment] */
const val ROUTER_PATH_SQUARE = "$ROUTER_GROUP_SQUARE/square"

/** 项目 [ProejectFragment] */
const val ROUTER_PATH_PROJECT = "$ROUTER_GROUP_PROJECT/project"

/** 我的 [MineFragment] */
const val ROUTER_PATH_MINE = "$ROUTER_GROUP_USER/mine"