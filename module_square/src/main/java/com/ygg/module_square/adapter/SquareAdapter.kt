package com.ygg.module_square.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ygg.lib_common.entity.SquareEntity
import com.ygg.module_square.R
import com.ygg.module_square.databinding.SquareItemHomeBinding
import com.ygg.module_square.viewmodel.SquareViewModel

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
class SquareAdapter(val viewModel: SquareViewModel) :
    BaseQuickAdapter<SquareEntity, BaseDataBindingHolder<SquareItemHomeBinding>>(
        R.layout.square_item_home
    ) {

    val tvShare = " 分享 "
    val tvAuthor = " 作者 "
    override fun convert(
        holder: BaseDataBindingHolder<SquareItemHomeBinding>,
        item: SquareEntity
    ) {
        holder.dataBinding?.apply {
            data = item
            adapter = this@SquareAdapter
            executePendingBindings()
        }
    }

    val diffConfig = object : DiffUtil.ItemCallback<SquareEntity>() {
        override fun areItemsTheSame(
            oldItem: SquareEntity,
            newItem: SquareEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SquareEntity,
            newItem: SquareEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

    }

    /** 注册点击 */
    val squareCollectClick: (SquareEntity) -> Unit = {
        viewModel.squareCollect(it)
    }
}