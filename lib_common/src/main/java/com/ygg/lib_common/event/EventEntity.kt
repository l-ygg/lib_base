package com.ygg.lib_common.event

import com.jeremyliao.liveeventbus.core.LiveEvent

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/4
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
data class RegisterSuccessEvent(val account: String, val pwd: String): LiveEvent