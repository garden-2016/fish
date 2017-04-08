package com.fish.learn.controller;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author noName
 * @since
 */
public abstract class BaseController {

    protected final static String DO_MAIN = "shci.cn.com";

    protected final static String ROOT_IMG_PATH = StringUtils.join( ClassLoader.getSystemResource("").getPath() , "static" , File.separator , "images" , File.separator , "user" );

    protected final static String IMG_PATH = StringUtils.join( "images" , File.separator , "user" );

}
