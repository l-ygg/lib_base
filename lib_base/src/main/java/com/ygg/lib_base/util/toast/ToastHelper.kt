package com.ygg.lib_base.util.toast

import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.Utils
import es.dmoral.toasty.Toasty

/**
 * Copyright (C) 2021 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2021/12/30
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */

fun showWarnToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.warning(Utils.getApp(), it.toString()).show()
        }
    }
}

fun showWarnLongToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.warning(Utils.getApp(), it.toString(), Toasty.LENGTH_LONG).show()
        }
    }
}

fun showInfoToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.info(Utils.getApp(), it.toString()).show()
        }
    }
}

fun showInfoLongToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.info(Utils.getApp(), it.toString(), Toasty.LENGTH_LONG).show()
        }
    }
}

fun showNormalToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.normal(Utils.getApp(), it.toString()).show()
        }
    }
}

fun showNormalLongToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.normal(Utils.getApp(), it.toString(), Toasty.LENGTH_LONG).show()
        }
    }
}

fun showErrorToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.error(Utils.getApp(), it.toString()).show()
        }
    }
}

fun showErrorLongToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.error(Utils.getApp(), it.toString(), Toasty.LENGTH_LONG).show()
        }
    }
}

fun showSuccessToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.success(Utils.getApp(), it.toString()).show()
        }
    }
}

fun showSuccessLongToast(msg: Any?) {
    msg?.let {
        ThreadUtils.runOnUiThread {
            Toasty.success(Utils.getApp(), it.toString(), Toasty.LENGTH_LONG).show()
        }
    }
}
