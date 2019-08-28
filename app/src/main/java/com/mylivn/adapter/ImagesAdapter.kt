package com.mylivn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mylivn.R

class ImagesAdapter(val mContext: Context) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(mContext)
    val images = arrayListOf<String>()

    init {
        initImageSample()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.image_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindImage(images[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindImage(url: String) {
        }
    }

    private fun initImageSample() {
        images.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/cat.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/pool.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/monarch.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/frymire.png")
        images.add("https://homepages.cae.wisc.edu/~ece533/images/sails.png")
    }
}