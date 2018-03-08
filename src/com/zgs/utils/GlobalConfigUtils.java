package com.zgs.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/3/5 15:15
 */
public class GlobalConfigUtils {
    public static void main(String[] args) {
        System.out.println(getAllConfig().toJSONString());
    }
    public static JSONArray getPortByIp(String ip){
        JSONObject serverConfig=getServerConfig();
        JSONArray  serverArray=serverConfig.getJSONArray(ip);
        JSONArray  protArray=new JSONArray();
        protArray.add("全部端口");
        for(int i=0;i<serverArray.size();i++){
            protArray.add(serverArray.getJSONObject(i).getString("port"));
        }
        return protArray;
    }
    public static JSONObject getServerConfig(){
        return getAllConfig().getJSONObject("serverConfig");
    }
    public static JSONArray getServerList(){
        return getAllConfig().getJSONArray("serverList");
    }
    public static JSONObject getAllConfig(){
        try{
            InputStream in= GlobalConfigUtils.class.getResourceAsStream("/com/zgs/config/serverlist.json");
            String result = new BufferedReader(new InputStreamReader(in,"UTF-8")).lines().collect(Collectors.joining("\n"));
            return JSONObject.parseObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
