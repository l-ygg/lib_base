package com.ygg.lib_common.entity

import androidx.databinding.ObservableBoolean
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/13
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
@Serializable
data class SquareListEntity(
    val curPage: String? = "",
    val offset: String? = "",
    val pageCount: String? = "",
    val size: String? = "",
    val total: String? = "",
    val over: String? = "",
    val datas: ArrayList<SquareEntity>? = arrayListOf()
)

@Serializable
data class SquareEntity(
    val apkLink: String? = "",
    val audit: String? = "",
    val author: String? = "",
    val canEdit: String? = "",
    val chapterId: String? = "",
    val chapterName: String? = "",
    val collect: String? = "",
    val courseId: String? = "",
    val desc: String? = "",
    val descMd: String? = "",
    val envelopePic: String? = "",
    val fresh: String? = "",
    val host: String? = "",
    val id: String? = "",
    val link: String? = "",
    val niceDate: String? = "",
    val niceShareDate: String? = "",
    val origin: String? = "",
    val prefix: String? = "",
    val projectLink: String? = "",
    val publishTime: String? = "",
    val realSuperChapterId: String? = "",
    val selfVisible: String? = "",
    val shareDate: String? = "",
    val shareUser: String? = "",
    val superChapterId: String? = "",
    val superChapterName: String? = "",
    val title: String? = "",
    val type: String? = "",
    val userId: String? = "",
    val visible: String? = "",
    val zan: Int,
) {
    /** 标记 - 是否收藏 */
    @Transient
    var collected: ObservableBoolean = ObservableBoolean(false)
}