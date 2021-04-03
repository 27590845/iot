package com.xidian.iot.dataapi.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @description:
 * @author: mrl
 * @date: 2021/4/2 下午11:23
 */
@Slf4j
public class HttpUtil {

    public enum Method{
        GET, DELETE
    }

    /**
     * get请求
     * 返回httpcode
     */
    public static int sendHttpRequest(String sURL, Map<String, String> header)  {
        int httpRespCode = -1;
        StringBuffer sResult = new StringBuffer();
        BufferedReader reader = null;
        HttpURLConnection uc = null;
        long startTime=System.currentTimeMillis();//记录开始时间

        try {
            URL url = new URL(sURL);
            uc = (HttpURLConnection) url.openConnection();
            setHeader(uc, header);

            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            httpRespCode = uc.getResponseCode();
            // 接收应答信息
            reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String curLine = "";
            while((curLine = reader.readLine())!=null){
                String reads = new String(curLine.getBytes(), 0, curLine.getBytes().length, "UTF-8");
                sResult.append(reads);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {}
                reader = null;
            }
            if (uc != null) {
                uc.disconnect();
                uc = null;
            }
        }
        long endTime=System.currentTimeMillis();//记录结束时间
        log.debug("HTTP操作耗时:" + (endTime-startTime) + "ms,responseCode:" + httpRespCode +",请求回来的数据:" + sResult.toString());
        return httpRespCode;
    }

    /**
     * get 请求
     */
    public static String sendHttpRequestForContent(String sURL, Map<String, String> header, Method method)  {
        int httpRespCode = -1;
        StringBuffer sResult = new StringBuffer();
        BufferedReader reader = null;
        HttpURLConnection uc = null;
        long startTime=System.currentTimeMillis();//记录开始时间

        log.debug("HTTP操作,URL:" + sURL);
        try {
            URL url = new URL(sURL);
            uc = (HttpURLConnection) url.openConnection();
            setHeader(uc, header);
            switch (method){
                case DELETE:
                    uc.setRequestMethod("DELETE");
                    break;
                case GET:
                default:
                    uc.setRequestMethod("GET");
                    break;
            }

            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            httpRespCode = uc.getResponseCode();
            // 接收应答信息
            reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String curLine = "";
            while((curLine = reader.readLine())!=null){
                String reads = new String(curLine.getBytes(), 0, curLine.getBytes().length, "UTF-8");
                sResult.append(reads);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {}
                reader = null;
            }
            if( uc != null){
                uc.disconnect();
                uc = null;
            }
        }
        long endTime=System.currentTimeMillis();//记录结束时间
        log.debug("HTTP操作耗时:" + (endTime-startTime) + "ms,responseCode:" + httpRespCode +",请求回来的数据:" + sResult.toString());
        return sResult.toString();
    }

    /**
     * post请求
     * param 请求body
     */
    public static String sendPost(String url, String param, Map<String, String> header) {
        log.info("HTTPPOST url:" + url + ",PARAM:" + param);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer sResult = new StringBuffer();
        long startTime=System.currentTimeMillis();//记录开始时间
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            setHeader(conn, header);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sResult.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out!=null) {
                    out.close();
                }
                if (in!=null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();//记录结束时间
        log.debug("HTTP操作耗时:" + (endTime-startTime) + "ms,请求回来的数据:" + sResult.toString());
        return sResult.toString();
    }

    private static void setHeader(URLConnection conn, Map<String, String> header){
        if(conn==null) return;
        // 设置通用的请求属性
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent",
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        if(header!=null && !header.isEmpty()){
            header.entrySet().forEach(entry -> conn.setRequestProperty(entry.getKey(), entry.getValue()));
        }
    }
}


