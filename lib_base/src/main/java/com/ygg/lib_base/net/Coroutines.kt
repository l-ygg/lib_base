package com.ygg.lib_base.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：在 [Dispatchers.IO] 线程执行 [block] 进行网络请求，超时时间为 [REQUEST_TIMEOUT]
 * <p>
 * <p>
 * 作者：lengyang 2021/12/31
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
suspend fun <T> netRequest(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.IO) {
        withTimeout(10_000L) {
            block.invoke(this)
        }
    }
}