package com.mylivn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.mylivn.R
import com.mylivn.lazyload.ImageLazyLoad
import com.mylivn.lazyload.config.Config
import com.mylivn.ui.adapter.ImagesAdapter
import com.mylivn.utils.Utility
import kotlinx.android.synthetic.main.activity_images.*

class ImagesActivity : AppCompatActivity() {

    private lateinit var mAdapter: ImagesAdapter

    private var imagesList = arrayOf(
        "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
        "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
        "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
        "https://homepages.cae.wisc.edu/~ece533/images/cat.png",
        "https://homepages.cae.wisc.edu/~ece533/images/monarch.png")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        val numberOfColumns = applicationContext?.let { Utility.calculateNoOfColumns(it, 150.0f) }
        recyclerImages.layoutManager = applicationContext?.let {
            numberOfColumns?.let { it1 -> GridLayoutManager(it, it1) }
        }

        recyclerImages.setHasFixedSize(true)

        imageLoader = ImageLazyLoad.getInstance(this,
            Config.defaultCacheSize)

        mAdapter = ImagesAdapter(applicationContext, imagesList,imageLoader)
        recyclerImages.adapter = mAdapter
    }

    private lateinit var imageLoader: ImageLazyLoad
}