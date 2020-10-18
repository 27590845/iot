package com.xidian.iot.common.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author wmr
 * @date 2020/9/27 10:01 上午
 */
public class CityCodeUtil {
    //百度地图API账号许可
    private static String ak="jhPGtfGrTzyoMNBRi1PVtkODgfelXofF";
    /**
     * 通过百度地图API获取当前经纬度的区域代码
     * @param lat 纬度
     * @param lng 经度
     * */
    public static String getCityCode(String lat,String lng){
        String url = "http://api.map.baidu.com/geocoder/v2/?ak="+ak+"&output=json&location="+lat+","+lng;
        URL myURL = null;
        URLConnection httpsConn = null;
        String result = null;
        try{
            myURL = new URL(url);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try{
            httpsConn = myURL.openConnection();
            if(httpsConn!=null){
                insr = new InputStreamReader(httpsConn.getInputStream(),"UTF-8");
                br = new BufferedReader(insr);
                String data = br.readLine();
                JSONObject jsonObject = JSONObject.parseObject(data);
                result = jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("adcode");
            }
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getCityCode("30.548397","104.04701"));
    }
}
