package com.mylivn.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.mylivn.R
import com.mylivn.lazyload.ImageLazyLoad

class ImagesAdapter(
    mContext: Context?,
    private val imagesList: Array<String>,
    val imageLoader: ImageLazyLoad
) :
    RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = imagesList[position]
        imageLoader.displayImage(model, holder.image, R.mipmap.ic_launcher)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}