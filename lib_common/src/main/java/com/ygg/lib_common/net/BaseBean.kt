package com.ygg.lib_common.net

import com.ygg.lib_base.net.BaseResponse
import java.io.Serializable

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
data class BaseBean<T>(val errorCode: Int, val errorMsg: String, val data: T) : BaseResponse<T>() {

    override fun isSucces(): Boolean {
        if (errorCode == 0) {
            return true
        }
        return false
    }

    override fun getResponseCode() = errorCode

    override fun getResponseData() = data

    override fun getResponseMsg() = errorMsg

}