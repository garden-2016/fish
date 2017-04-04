package com.fish.learn.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by OutDog on 04/04/2017.
 */
@Data
public class Response<T> implements Serializable{

    private T data;

    private boolean success;

    private String message;

    public static Response ok(){
        return new Response();
    }

    public static Response ok( Object data ){
        return new Response( data , true , StringUtils.EMPTY );
    }

    public Response(){
        this.data = null;
        this.success = true;
        this.message = StringUtils.EMPTY;
    }

    public Response(T data , boolean success , String message){
        this.data = data;
        this.success = success;
        this.message = message;
    }

}
