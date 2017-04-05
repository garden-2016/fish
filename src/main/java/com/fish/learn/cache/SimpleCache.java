package com.fish.learn.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author noName
 * @since 简单的缓存
 */
@Component
public class SimpleCache {

    private Map<String , String> simpleCache = new ConcurrentHashMap();

    public String get(String key){
        return simpleCache.get(key);
    }

    public void put(String key , String value){
        simpleCache.put(key , value);
    }

    public void clear(){
        simpleCache.clear();
    }

}
