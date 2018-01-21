package com.contactlist.utils;

import android.util.LruCache;

import java.util.Map;

public class Cache {
    private static LruCache<String, Object> mCacheData = new LruCache<>(80);

    public void put(String id, Object object) {
        this.mCacheData.put(id, object);
    }

    public Object get(String id) {
        return this.mCacheData.get(id);
    }

    public Object remove(String id) {
        return this.mCacheData.remove(id);
    }

    public int size() {
        return this.mCacheData.size();
    }

    private static Cache sICache;

    public static Cache getInstance() {

        if (sICache == null) {
            sICache = new Cache();
        }

        return sICache;
    }

    public Map<String, Object> getSnapShot() {
        return mCacheData.snapshot();
    }

    public void clear() {
        mCacheData.evictAll();
    }
}