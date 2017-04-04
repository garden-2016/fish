package com.fish.learn.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fish.learn.model.BaseModel;
import com.fish.learn.model.Resume;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author noName
 * @since 基础增删改查操作
 * @param <T> 命名空间
 */
public class BaseJsonDao<T extends BaseModel> {

    public final static String BASE_DB_PATH = ClassLoader.getSystemResource("db-json").getPath();

    /**
     * 服务器上数据库真实地址
     */
    @Getter
    private final String simpleNameSpace;

    /**
     * 简称
     */
    @Getter
    private final String nameSpace;

    public BaseJsonDao(){
        this.simpleNameSpace = this.getParameterizedType().getSimpleName();
        this.nameSpace = StringUtils.join( BASE_DB_PATH , File.separator , simpleNameSpace , ".json" );
    }

    /**
     * 获取范型类型
     * @return
     */
    private Class<T> getParameterizedType(){
        if(this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            return ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } else {
            return ((Class)((ParameterizedType)this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }
    }

    /**
     * 获取资源
     * @return
     * @throws Exception
     */
    public synchronized String getSourceAsString() throws Exception{
        String jsonData = "[]";
        try (
                BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( nameSpace )));
        ){
                jsonData = br.readLine();
        }
        return jsonData;
    }

    public List<T> getAll() throws Exception{
        return JSONArray.parseArray( this.getSourceAsString() , this.getParameterizedType() );
    }

    /**
     * 返回id
     * @param t
     * @return
     * @throws Exception
     */
    public int insert(T t) throws Exception{
        int id = IdUtils.getId();
        t.setId(id);
        List<T> allData =  this.getAll();
        allData.add( t );
        writeSource(allData);
        return id;
    }

    public void update( T t )throws Exception{
        List<Resume> allData = JSONArray.parseArray( this.getSourceAsString() , Resume.class );
        for( Resume data : allData ){
            if( data != null && data.getId() == t.getId() ){
                BeanUtils.copyProperties( t , data );
            }
        }
        this.writeSource((List<T>) allData);
    }

    public void delete(int id) throws Exception {
        List<T> allData = this.getAll();
        for( int i = 0 ; i < allData.size() ; ++i ){
            if( allData.get(i) != null && allData.get(i).getId() == id ){
                allData.remove(i);
                break;
            }
        }
        this.writeSource(allData);
    }

    /**
     * 全量覆盖
     * @param data
     */
    public synchronized void writeSource(List<T> data) throws Exception{
        try(
                BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( nameSpace ) ) );
        ){
                bw.write( JSONObject.toJSONString(data) );
        }
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    public Resume getById(int id) throws Exception{
        List<Resume> allData = JSONArray.parseArray( this.getSourceAsString() , Resume.class );
        for( Resume data : allData ){
            if( data != null && data.getId() == id ){
                return data;
            }
        }
        return null;
    }


}
