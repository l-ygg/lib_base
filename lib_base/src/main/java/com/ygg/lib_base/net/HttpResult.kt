package com.ygg.lib_base.net

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.ygg.lib_base.base.BaseViewModel
import com.ygg.lib_base.model.UiLoadingDialogModel
import com.ygg.lib_base.util.toast.showErrorToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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


/**
 * 过滤服务器结果，失败抛异常
 * @param block 请求体方法，必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不传
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "加载中"
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowDialog) uiLoadingDialog.value = UiLoadingDialogModel(true, loadingMessage)
            block()
        }.onSuccess {
            if (isShowDialog) uiLoadingDialog.value = UiLoadingDialogModel(false)
            kotlin.runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t ->
                    success(t)
                }
            }.onFailure {
                LogUtils.e(it.message)
                //失败回调
                error(ExceptionHandle.handleException(it))
                showErrorToast(ExceptionHandle.handleException(it).errorMsg)
            }
        }.onFailure {
            if (isShowDialog) uiLoadingDialog.value = UiLoadingDialogModel(false)
            //失败回调
            LogUtils.e(it.message)
            error(ExceptionHandle.handleException(it))
            showErrorToast(ExceptionHandle.handleException(it).errorMsg)
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        when {
            response.isSucces() -> {
                success(response.getResponseData())
            }
            else -> {
                throw AppException(
                    response.getResponseCode(),
                    response.getResponseMsg(),
                    response.getResponseMsg()
                )
            }
        }
    }
}