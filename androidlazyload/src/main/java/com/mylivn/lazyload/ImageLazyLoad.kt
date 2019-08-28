package com.mylivn.lazyload

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.mylivn.lazyload.cache.CacheRepository
import com.mylivn.lazyload.config.Config
import com.mylivn.lazyload.taskDowload.DownloadImageTask
import com.mylivn.lazyload.taskDowload.DownloadTask
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ImageLazyLoad private constructor(context: Context, cacheSize: Int) {

    private val cache = CacheRepository(context, cacheSize)
    private val executorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    private val mRunningDownloadList: HashMap<String, Future<Bitmap?>> = hashMapOf()

    fun displayImage(url: String, imageview: ImageView, placeholder: Int) {
        var bitmap = cache.get(url)
        bitmap?.let {
            imageview.setImageBitmap(it)
            return
        }
            ?: run {
                imageview.tag = url
                imageview.setImageResource(placeholder)
                addDownloadImageTask(url, DownloadImageTask(url, imageview, cache))
            }
    }


    fun addDownloadImageTask(url: String, downloadTask: DownloadTask<Bitmap?>) {
        mRunningDownloadList.put(url, executorService.submit(downloadTask))
    }

    fun clearcache() {
        cache.clear()
    }

    fun cancelTask(url: String) {
        synchronized(this) {
            mRunningDownloadList.forEach {
                if (it.key == url && !it.value.isDone)
                    it.value.cancel(true)
            }
        }
    }

    fun cancelAll() {
        synchronized(this) {
            mRunningDownloadList.forEach {
                if (!it.value.isDone)
                    it.value.cancel(true)
            }
            mRunningDownloadList.clear()
        }
    }

    companion object {
        private val INSTANCE: ImageLazyLoad? = null
        @Synchronized
        fun getInstance(context: Context, cacheSize: Int = Config.defaultCacheSize): ImageLazyLoad {
            return INSTANCE?.let { return INSTANCE }
                ?: run {
                    return ImageLazyLoad(context, cacheSize)
                }
        }
    }
}