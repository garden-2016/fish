package com.fish.learn.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @author noName
 * @since id生成器
 */
@Slf4j
public final class IdUtils {

    private static File ID_HARD = new File(ClassLoader.getSystemResource(StringUtils.join("db-json", File.separator , "id" )).getFile());

    public static synchronized int getId(){
        int id = 0;
        try
        {
            id = _getId();
            generateNext(id);
        }catch (Exception e){
            log.error( "" , e );
        }
        return id;
    }

    private static synchronized int _getId() throws Exception{
        int id = 0;
        try(
                BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( ID_HARD ) ));
        )
        {
            id = Integer.valueOf(br.readLine());
        }
        return id;
    }

    private static synchronized void generateNext(int id) throws Exception{
        try(
                BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( ID_HARD ) ) );
        )
        {
            int next = id + 1;
            bw.write( String.valueOf(next) );
            bw.flush();
        }
    }

}
