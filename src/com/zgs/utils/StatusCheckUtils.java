package com.zgs.utils;

import com.sun.deploy.util.StringUtils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/3/6 16:55
 */
public class StatusCheckUtils {
    public static boolean healthCheck(String url){
        boolean status=false;
        if(url==null||"".equals(url.trim())){
            return status;
        }
        try{
            // 建立连接
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            //设置超时时间
            conn.setConnectTimeout(5000);
            conn.connect();
            if(200==conn.getResponseCode()){
                status=true;
            }
        }catch (Exception e){
//            e.printStackTrace();
        }
        return status;
    }
}
