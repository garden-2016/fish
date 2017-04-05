package com.fish.learn.aspect;

import com.fish.learn.cache.CacheHandle;
import com.fish.learn.cache.SimpleCache;
import com.fish.learn.model.BaseModel;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author noName
 */
@Slf4j
@Aspect
@Component
//@Order(0)
public class DaoCacheAspect {

    @Autowired
    private SimpleCache daoCache;

    @Pointcut( "execution(@com.fish.learn.cache.CacheHandle * com.fish.learn.dao..*.*(..))" )
    public void pointcut(){}

    @Around("pointcut()&&@annotation(cacheHandle)")
    public Object doAround(ProceedingJoinPoint pjp , CacheHandle cacheHandle){
        Object value = null;
        try{
            Object [] agrs = pjp.getArgs();
            Integer _cacheKey = SimpleCache.generateCacheKey( agrs );
            log.info( "cache key:{}" , _cacheKey );
            switch ( cacheHandle.value() ){
                case SELECT:
                   value = daoCache.get(_cacheKey);
                    if( value == null ){
                        value = pjp.proceed(agrs);
                        daoCache.put( _cacheKey , value );
                    }
                    break;
                case UPDATE:
                    value = pjp.proceed(agrs);
                    if( agrs != null && agrs.length == 1 ){
                        Object update = agrs[0];
                        if( update instanceof BaseModel){
                            daoCache.put( ((BaseModel) update).getId() , update );
                        }
                    }
                    break;
                case DELETE:
                    value = pjp.proceed(agrs);
                    daoCache.remove( _cacheKey );
                    break;
            }
        }catch (Throwable e){
            log.error(Throwables.getStackTraceAsString(e));
        }
        return value;
    }

}
