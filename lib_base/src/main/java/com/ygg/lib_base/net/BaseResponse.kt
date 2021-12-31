package com.ygg.lib_base.net

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：  服务器返回数据的基类
 * 如果需要框架帮你做脱壳处理请继承它！！请注意：
 * 2.必须实现抽象方法，根据自己的业务判断返回请求结果是否成功
 * <p>
 * <p>
 * 作者：lengyang 2021/12/31
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
abstract class BaseResponse<T> {

    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSucces(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}