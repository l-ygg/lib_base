package com.ygg.module_main.adapter

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ygg.lib_base.model.UiStartActModel
import com.ygg.lib_common.constants.ROUTER_PATH_REGISTER
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.lib_common.interfaces.collect
import com.ygg.module_main.R
import com.ygg.module_main.databinding.MainItemHomeBinding
import com.ygg.module_main.ui.HomeFragment
import com.ygg.module_main.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/7
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class HomeArticleAdapter(val viewModel: HomeViewModel) :
    BaseQuickAdapter<ArticleEntity, BaseDataBindingHolder<MainItemHomeBinding>>(
        R.layout.main_item_home
    ) {

    val tvShare = " 分享 "
    val tvAuthor = " 作者 "

    override fun convert(holder: BaseDataBindingHolder<MainItemHomeBinding>, item: ArticleEntity) {
        holder.dataBinding?.apply {
            data = item
            adapter = this@HomeArticleAdapter
            executePendingBindings()
        }
    }

    val diffConfig = object : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(
            oldItem: ArticleEntity,
            newItem: ArticleEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArticleEntity,
            newItem: ArticleEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    /** 注册点击 */
    val collectClick: (ArticleEntity) -> Unit = {
        viewModel.articleCollect(it)
    }
}