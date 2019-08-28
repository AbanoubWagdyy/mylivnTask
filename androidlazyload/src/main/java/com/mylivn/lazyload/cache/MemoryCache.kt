package com.mylivn.lazyload.cache

import android.graphics.Bitmap
import android.util.LruCache
import com.mylivn.lazyload.config.Config

class MemoryCache(newMaxSize: Int) : ImageCache {
    private val cache: LruCache<String, Bitmap>

    init {
        var cacheSize: Int
        if (newMaxSize > Config.maxMemory) {
            cacheSize = Config.defaultCacheSize
        } else {
            cacheSize = newMaxSize
        }
        cache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return (value.rowBytes) * (value.height) / 1024
            }
        }
    }

    override fun put(url: String, bitmap: Bitmap) {
        cache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        return cache.get(url)
    }

    override fun clear() {
        cache.evictAll()
    }
}