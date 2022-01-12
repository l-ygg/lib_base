package com.ygg.module_main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ygg.lib_common.entity.ProjectEntity
import com.ygg.module_main.R
import com.ygg.module_main.databinding.MainItemHomeBinding
import com.ygg.module_main.databinding.MainItemProjectBinding
import com.ygg.module_main.viewmodel.HomeViewModel

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/12
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class HomeProjectAdapter(val viewModel: HomeViewModel) :
    BaseQuickAdapter<ProjectEntity, BaseDataBindingHolder<MainItemProjectBinding>>(
        R.layout.main_item_project
    ) {

    override fun convert(
        holder: BaseDataBindingHolder<MainItemProjectBinding>,
        item: ProjectEntity
    ) {
        holder.dataBinding?.apply {
            data = item
            adapter = this@HomeProjectAdapter
            executePendingBindings()
        }
    }

    val diffConfig = object : DiffUtil.ItemCallback<ProjectEntity>() {
        override fun areItemsTheSame(
            oldItem: ProjectEntity,
            newItem: ProjectEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProjectEntity,
            newItem: ProjectEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

}