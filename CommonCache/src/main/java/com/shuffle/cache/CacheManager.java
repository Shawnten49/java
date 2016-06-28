package com.shuffle.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:Shawn.Xu
 * Date:2016/5/23
 * Description:
 */
public class CacheManager<T> {
    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T getValue(String key) {
        return  cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    public void remove(String key) {
        if (cache.containsValue(key)) {
            cache.remove(key);
        }
    }

    public void clear() {
        cache.clear();
    }
}
