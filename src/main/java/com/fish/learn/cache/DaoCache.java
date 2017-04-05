package com.fish.learn.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by OutDog on 05/04/2017.
 */
@Component
public class DaoCache {

    @Autowired
    private SimpleCache originCache;



}
