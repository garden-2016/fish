package com.fish.learn.controller;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.UUID;

/**
 * @author noName
 * @since
 */
public abstract class BaseController {

    /**
     * 主域名
     */
    protected final static String DO_MAIN = "shci.cn.com";

    /**
     * 图频存储根路径
     */
    protected final static String ROOT_IMG_PATH = StringUtils.join( ClassLoader.getSystemResource("").getPath() , "static" , File.separator , "images" , File.separator , "user" );

    /**
     * 图片存储相对路径
     */
    protected final static String IMG_PATH = StringUtils.join( "images" , File.separator , "user" );

    /**
     * 根据用户id 计算二维码访问地址
     * @param id
     * @return
     */
    protected final String verifyImgShowUrl(int id){
        return StringUtils.join( "http://" , DO_MAIN , "view/forphone.html?id=" , id );
    }

    /**
     * 根据用户id 计算二维码存储地址
     * @param id
     * @return
     */
    protected final String verifyImgFileUrl(int id){
        return StringUtils.join( ROOT_IMG_PATH , id , File.separator , UUID.randomUUID().toString() , "jpg");
    }

}
