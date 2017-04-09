package com.fish.learn.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noName
 * @param <T>
 * @since 分页对象
 */
@Data
public final class Paging<T> implements Serializable {

    private List<T> data;

    private int count;

    public Paging(){
        this.count = 0;
        this.data = new ArrayList<T>();
    }

    public Paging(List<T> data , int count){
        this.data = data;
        this.count = count;
    }

    public void buildPaging(List<T> originData , Integer pageNo , Integer pageSize){

        pageNo = pageNo == null || pageNo < 0 ? 1 : pageNo;
        pageSize = pageSize == null || pageSize < 0 ? 10 : pageSize;

        this.count = originData.size();
        List<T> data = new ArrayList<T>(pageSize);
        Integer index = index( pageNo , pageSize );
        Integer end = index + pageSize - 1;
        while ( index < end && index < originData.size() ){
            data.add( originData.get(index) );
            index++;
        }
        this.data = data;

    }

    private Integer index(Integer pageNo , Integer pageSize){
        return ( pageNo - 1 ) * pageSize;
    }



}
