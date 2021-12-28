package com.ygg.lib_base.log

import android.util.Log
import com.ygg.lib_base.ext.getStackTraceString

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述： 内部打印log
 * <p>
 * <p>
 * 作者：lengyang 2021/12/27
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
object InternalLog {

    private var mPrinter: Printer = Printer.DEFAULT_PRINTER
    private var logEnable = false

    /**
     * 设置日志打印对象
     *
     * @param printer 日志打印对象
     */
    @JvmStatic
    fun printer(printer: Printer) {
        mPrinter = printer
    }

    /**
     * 设置日志开关
     *
     * @param logEnable 是否打印日志
     */
    @JvmStatic
    fun logEnable(logEnable: Boolean) {
        InternalLog.logEnable = logEnable
    }

    /**
     * 打印 ERROR 信息
     *
     * @param tag 临时标签
     * @param message 日志信息
     * @param args 格式化信息
     */
    @JvmStatic
    fun e(tag: String?, message: String, vararg args: Any) {
        if (logEnable) {
            mPrinter.log(Log.ERROR, tag, if (args.isEmpty()) message else String.format(message, *args), null)
        }
    }

    /**
     * 打印 ERROR 信息
     *
     * @param tag 临时标签
     * @param throwable 异常对象
     * @param message 日志信息
     * @param args 格式化信息
     */
    @JvmStatic
    fun e(tag: String?, throwable: Throwable?, message: String, vararg args: Any) {
        if (logEnable) {
            mPrinter.log(Log.ERROR, tag, if (args.isEmpty()) message else String.format(message, *args), throwable)
        }
    }

    /**
     * 打印 INFO 信息
     *
     * @param tag 临时标签
     * @param message 日志信息
     * @param args 格式化信息
     */
    @JvmStatic
    fun i(tag: String?, message: String, vararg args: Any) {
        if (logEnable) {
            mPrinter.log(Log.INFO, tag, if (args.isEmpty()) message else String.format(message, *args), null)
        }
    }

}

interface Printer {

    fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?)

    companion object {
        internal val DEFAULT_PRINTER: Printer = object : Printer {

            override fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
                var msg = message.orEmpty()
                if (throwable != null && message != null) {
                    msg += " : " + throwable.getStackTraceString()
                }
                if (throwable != null && message == null) {
                    msg = throwable.getStackTraceString()
                }
                if (message.isNullOrBlank()) {
                    msg = "Empty/NULL log message"
                }
                val tempTag = "lib_base_tag${if (tag == null || tag.isBlank()) "" else "-$tag"}"
                Log.println(priority, tempTag, msg)
            }
        }
    }
}