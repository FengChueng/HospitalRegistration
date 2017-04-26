package com.zyl.hospital.test;

import android.text.TextUtils;

import com.zyl.hospital.registration.utils.LogUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zhangyinglong on 2017/4/17.
 */
public class RequestUtil {

    public static String reqeustGet(String urlStr){
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(30*1000);
            urlConnection.setReadTimeout(30*1000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            LogUtils.i(urlConnection.getResponseCode());
            if(urlConnection.getResponseCode()==200){//请求成功
                InputStream inputStream = urlConnection.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while((len = inputStream.read(buffer))!= -1){
                    out.write(buffer,0,len);
                }
                out.close();
                inputStream.close();
                System.out.println("json:\n"+new String(out.toByteArray()));
                LogUtils.i(new String(out.toByteArray()));
                return new String(out.toByteArray());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
        } catch (ProtocolException e) {
            LogUtils.e(e.getMessage());
        } catch (IOException e) {
            LogUtils.e(e.getMessage());
        }
        return  "";
    }

    public static String reqeustParam(String urlStr, Map<String,String> requestBody){
        //String urlPath = new String("http://localhost:8080/Test1/HelloWorld?name=丁丁".getBytes("UTF-8"));
        String param= map2Url(requestBody);
        try {
            //建立连接
            URL url=new URL(urlStr);
            HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
            //设置参数
            httpConn.setDoOutput(true);   //需要输出
            httpConn.setDoInput(true);   //需要输入
            httpConn.setUseCaches(false);  //不允许缓存
            httpConn.setRequestMethod("POST");   //设置POST方式连接
            //设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpConn.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode=httpConn.getResponseCode();
            if(HttpURLConnection.HTTP_OK==resultCode){
                StringBuffer sb=new StringBuffer();
                String readLine=new String();
                BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
                while((readLine=responseReader.readLine())!=null){
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                System.out.println(sb.toString());
                return sb.toString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * map转url参数
     */
    public static String map2Url(Map<String, String> paramToMap) {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url    = new StringBuffer();
        boolean      isfist = true;
        for (Map.Entry<String, String> entry : paramToMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (!TextUtils.isEmpty(value)) {
                try {
                    url.append(URLEncoder.encode(value, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return url.toString();
    }
}