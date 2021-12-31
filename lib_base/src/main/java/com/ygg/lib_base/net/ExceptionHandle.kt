package com.ygg.lib_base.net

import android.net.ParseException
import android.util.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import com.google.gson.JsonParseException

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
object ExceptionHandle {

    fun handleException(e: Throwable?): AppException {
        val ex: AppException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = AppException(Error.NETWORK_ERROR,e)
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(Error.PARSE_ERROR,e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppException(Error.NETWORK_ERROR,e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = AppException(Error.SSL_ERROR,e)
                    return ex
                }
                is ConnectTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR,e)
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR,e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = AppException(Error.TIMEOUT_ERROR,e)
                    return ex
                }
                is AppException -> return it

                else -> {
                    ex = AppException(Error.UNKNOWN,e)
                    return ex
                }
            }
        }
        ex = AppException(Error.UNKNOWN,e)
        return ex
    }
}