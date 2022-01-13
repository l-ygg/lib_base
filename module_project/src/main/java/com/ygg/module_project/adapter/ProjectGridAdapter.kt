package com.ygg.module_project.adapter

import android.util.SparseArray
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import coil.ImageLoader
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.Utils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ygg.lib_base.base.BaseFragment
import com.ygg.lib_base.ext.getImageBitmapByUrl
import com.ygg.lib_base.ext.loadImage
import com.ygg.lib_base.net.netRequest
import com.ygg.lib_common.entity.ArticleEntity
import com.ygg.module_project.R
import com.ygg.module_project.databinding.ProjectItemGridBinding
import com.ygg.module_project.ui.ContentFragment
import com.ygg.module_project.viewmodel.ContentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
class ProjectGridAdapter(private val fragment: ContentFragment) :
    BaseQuickAdapter<ArticleEntity, BaseDataBindingHolder<ProjectItemGridBinding>>(R.layout.project_item_grid) {

    // 存储图片的高度
    private val sizeSparseArray: SparseArray<Int?> = SparseArray()

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
            return oldItem == newItem
        }
    }

    override fun convert(
        holder: BaseDataBindingHolder<ProjectItemGridBinding>,
        item: ArticleEntity
    ) {
        holder.dataBinding?.apply {
            data = item
            adapter = this@ProjectGridAdapter

            // 设置图片宽高
            val layoutParams = ivProjectItem.layoutParams
            val screenWidth = ScreenUtils.getAppScreenWidth()
            val mWidth = screenWidth / 2
            var mHeight = 100
            layoutParams.width = mWidth
            if (sizeSparseArray.get(getItemPosition(item)) != null) {
                mHeight = sizeSparseArray.get(getItemPosition(item))!!
                layoutParams.height = mHeight!!
                ivProjectItem.layoutParams = layoutParams
            }

            fragment.viewModel.viewModelScope.launch {
                var bitmap = fragment.context?.getImageBitmapByUrl(item.envelopePic!!)
                //获取原图的宽高
                val width = bitmap?.width!!
                val height = bitmap?.height!!

                //获取imageView的宽
                val imageViewWidth = ivProjectItem.width
                //计算缩放比例
                val sy = (imageViewWidth * 0.1).toFloat() / (width * 0.1).toFloat()
                //计算图片等比例放大后的高
                val imageViewHeight = (height * sy).toInt()
                val params = ivProjectItem.layoutParams
                params.height = imageViewHeight
                ivProjectItem.layoutParams = params
                val itemPosition = getItemPosition(item)
                if (sizeSparseArray.get(itemPosition) == null) {
                    sizeSparseArray.put(itemPosition, imageViewHeight)
                }

//                var imageRequest = ImageRequest.Builder(Utils.getApp())
//                    .data(item.envelopePic)
//                    .size(params.width, params.height)
//                    .build()
//                context.imageLoader.enqueue(imageRequest)
                ivProjectItem.load(item.envelopePic)
            }

            executePendingBindings()
        }
    }
}