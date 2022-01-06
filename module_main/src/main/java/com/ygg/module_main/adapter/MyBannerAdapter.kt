package com.ygg.module_main.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ygg.lib_base.ext.loadImage
import com.ygg.lib_common.entity.BannerEntity
import com.ygg.module_main.R
import com.ygg.module_main.ui.HomeFragment
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter

/**
 * Copyright (C) 2022 重庆呼我出行网络科技有限公司
 * 版权所有
 * <p>
 * 功能描述：
 * <p>
 * <p>
 * 作者：lengyang 2022/1/6
 * <p>
 * 修改人：
 * 修改描述：
 * 修改日期
 */
class MyBannerAdapter(
    mData: List<BannerEntity?>,
    private val homeFragment: HomeFragment
) :
    BannerAdapter<BannerEntity, MyBannerAdapter.ImageTitleHolder>(mData) {

    private var mDiffer: AsyncListDiffer<BannerEntity>

    init {
        val diffCallback: DiffUtil.ItemCallback<BannerEntity> =
            object : DiffUtil.ItemCallback<BannerEntity>() {
                override fun areItemsTheSame(
                    oldItem: BannerEntity,
                    newItem: BannerEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: BannerEntity,
                    newItem: BannerEntity
                ): Boolean {
                    return TextUtils.equals(oldItem.imagePath, newItem.imagePath)
                }
            }
        mDiffer = AsyncListDiffer(this, diffCallback)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageTitleHolder {
        return ImageTitleHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_banner_item, parent, false)
        )
    }

    override fun onBindView(
        holder: ImageTitleHolder,
        data: BannerEntity,
        position: Int,
        size: Int
    ) {
        holder.imageView.loadImage(data.imagePath)
        holder.title.text = data.title
        holder.imageView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(AppConstants.BundleKey.WEB_URL, data.url)
//            homeFragment.viewModel.startContainerActivity(AppConstants.Router.Web.F_WEB,bundle)
        }
    }

    class ImageTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image)
        var title: TextView = view.findViewById(R.id.bannerTitle)
    }

    private fun submitList(data: List<BannerEntity>?) {
        mDiffer.submitList(data)
    }

    fun setData(
        banner: Banner<Any, BannerAdapter<*, *>>,
        data: List<BannerEntity>?
    ) {
        if (data == mDatas) {
            return
        }
        // 重新赋值新数据到banner
        setDatas(data)
        // 计算差异并赋值新数据
        submitList(data)
        // 设置banner
        banner.apply {
            setCurrentItem(1, false)
            setIndicatorPageChange()
        }
    }

    fun getItem(position: Int): BannerEntity? {
        return mDiffer.currentList[position]
    }

}