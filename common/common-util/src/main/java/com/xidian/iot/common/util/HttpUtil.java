package com.xidian.iot.common.util;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author mrl
 * @Title: HttpUtil
 * @Package com.xidian.iot.common.util
 * @Description: http request methods
 * @date 2020/9/1 8:08 下午
 */
public class HttpUtil {

    /**
     * 不使用代理使用的httpClient
     */
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static final String BODY_KEY = "body";
    public static final String HEADERS_KEY = "headers";

    /**
     * Ua常量
     */
    private static final String[] ua = {
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};


    private CloseableHttpClient proxyHttpClient;

    /**
     * 重试处理器
     */
    private static final HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            return false;
        }
    };


    /**
     * 获取http的httpClient
     *
     * @param ip
     * @param port
     * @return
     */
    public CloseableHttpClient getHttpClient(String ip, Integer port) {
        HttpHost proxy = new HttpHost(ip, port, "http");
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .setRetryHandler(myRetryHandler).build();
        return httpclient;
    }

    /**
     * 获取https的httpClient
     *
     * @param ip
     * @param port
     * @return
     */
    public CloseableHttpClient getHttpsClient(String ip, Integer port) {

        //这里设置客户端不检测服务器ssl证书
        try {
            X509TrustManager x509mgr = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509mgr}, null);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpHost proxy = new HttpHost(ip, port, "https");

            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(3000)
                    .setProxy(proxy)
                    .build();
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(defaultRequestConfig)
                    .setRetryHandler(myRetryHandler).build();
            return httpclient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取状态码
     *
     * @param url
     * @param ip
     * @param scheme
     * @param port
     * @return
     */
    public Integer getGetHttpStatusCode(String url, String ip, String scheme, Integer port) {
        if (scheme.equals("http")) {
            proxyHttpClient = getHttpClient(ip, port);
        } else if (scheme.equals("https")) {
            proxyHttpClient = getHttpsClient(ip, port);
        } else {
            return null;
        }

        String result = null;
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(url);

        // 浏览器表示,随机产生一个ua头
        Random r = new Random();
        int i = r.nextInt(14);
        httpget.addHeader("UserTest-Agent", ua[i]);

        // 传输的类型
        CloseableHttpResponse response = null;
        try {
            response = proxyHttpClient.execute(httpget);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());

        }

        if (response == null) {
            return null;
        } else {
            return response.getStatusLine().getStatusCode();
        }
    }

    /**
     * 向指定URL发送GET方式的请求,无参数
     *
     * @param url 发送请求的URL
     * @return URL 代表远程资源的响应
     */
    public String sendProxyGet(String url, String ip, String scheme, Integer port) {
        if (scheme.equals("http")) {
            proxyHttpClient = getHttpClient(ip, port);
        } else if (scheme.equals("https")) {
            proxyHttpClient = getHttpsClient(ip, port);
        } else {
            return null;
        }

        String result = null;
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(url);

        // 浏览器表示,随机产生一个ua头
        Random r = new Random();
        int i = r.nextInt(14);
        httpget.addHeader("UserTest-Agent", ua[i]);

        // 传输的类型
        CloseableHttpResponse response = null;
        try {
            response = proxyHttpClient.execute(httpget);
        } catch (Exception e1) {
            System.out.println(e1.getMessage() + "获取response失败");
        }
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println("返回结果失败" + e.getMessage());
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                System.out.println("response 关闭失败" + e.getMessage());
            }
        }

        return result;
    }

    /**
     * 向指定URL发送GET方式的请求,无参数, 返回body和heads
     *
     * @param url 发送请求的URL
     * @return URL 代表远程资源的响应
     */
    public static Map<String, Object> sendGetResponse(String url) {
        Map<String, Object> result = new HashMap<>();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(url);
        // 浏览器表示,随机产生一个ua头
        Random r = new Random();
        int i = r.nextInt(14);
        httpget.addHeader("UserTest-Agent", ua[i]);
        // 传输的类型
        httpget.addHeader("Content-Type", "application/x-www-form-urlencoded");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        httpget.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result.put(BODY_KEY, EntityUtils.toString(entity));
            }
            Header[] headers = response.getAllHeaders();
            if(null!=headers && headers.length>0){
                result.put(HEADERS_KEY, headers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                System.out.println("response 关闭失败");
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 向指定URL发送GET方式的请求,无参数，只返回body
     *
     * @param url 发送请求的URL
     * @return URL 代表远程资源的响应
     */
    public static String sendGet(String url) {
        String body = null;
        Map result = sendGetResponse(url);
        if(null!=result && result.containsKey(BODY_KEY)){
            body = (String) result.get(BODY_KEY);
        }
        return body;
    }

    /**
     * 向指定URL发送GET方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 代表远程资源的响应
     */
    public static String sendGet(String url, Map<String, String> param, String token) {
        StringBuilder params = new StringBuilder();
//        返回参数
        String result = null;
//        组装参数
        params.append(url);

        if (param != null) {
            params.append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                params.append(entry.getKey());
                params.append("=");
                try {
                    params.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    System.out.println("url编码错误");
                    params.append(entry.getValue());
                }

                params.append("&");
            }
        }

        params.append("accessToken=" + token);

        String urlName = params.toString();

        urlName = urlName.substring(0, urlName.length());


        //2.生成一个get请求
        HttpGet httpget = new HttpGet(urlName);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                System.out.println("response 关闭失败");
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 向指定URL发送GET方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 代表远程资源的响应
     */
    public static String sendGet(String url, Map<String, String> param) {
        StringBuilder params = new StringBuilder();
//        返回参数
        String result = null;
//        组装参数
        params.append(url);

        if (param != null) {
            params.append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                params.append(entry.getKey());
                params.append("=");
                try {
                    params.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    System.out.println("url编码错误");
                    params.append(entry.getValue());
                }

                params.append("&");
            }
        }
        String urlName = params.toString();
        urlName = urlName.substring(0, urlName.length() - 1);

        //2.生成一个get请求
        HttpGet httpget = new HttpGet(urlName);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                System.out.println("response 关闭失败");
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 发送HttpPost请求，参数为map地点
     *
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
