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

    private Map<Integer , Object> simpleCache = new ConcurrentHashMap<Integer , Object>();

    public Object get(Integer key){
        return simpleCache.get(key);
    }

    public void put(Integer key , Object value){
        simpleCache.put(key , value);
    }

    public void remove(Integer key){
        simpleCache.remove(key);
    }

    public void clear(){
        simpleCache.clear();
    }

    /**
     * 生成key
     * @param agrs 参数
     * @return
     */
    public static int generateCacheKey( Object [] agrs){
        Integer _cacheKey = Integer.MAX_VALUE;
        if( agrs == null || agrs.length > 0 ){
            for( int i = 0 ; i < agrs.length ; ++i ){
                if( agrs[i] != null ){
                    _cacheKey = _cacheKey & agrs[i].hashCode();
                }
            }
        }
        return _cacheKey;
    }

}
