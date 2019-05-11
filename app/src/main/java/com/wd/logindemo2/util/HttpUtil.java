package com.wd.logindemo2.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author dingtao
 * @date 2019/4/25 11:01
 * qq:1940870847
 */
public class HttpUtil {

    private static HttpUtil instance;
    private OkHttpClient okHttpClient;

    private HttpUtil(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public static HttpUtil getInstance() {
        if (instance == null){
            instance = new HttpUtil();
        }
        return instance;
    }

    public String getByOKHttp(String url){
        return getByOKHttp(url,null,null);
    }

    public String getByOKHttp(String url,String[] keys,String[] values){
        if (keys!=null){
            url+="?";
            for (int i = 0; i <keys.length ; i++) {
                if (i!=keys.length-1){
                    url=url+keys[i]+"="+values[i]+"&";
                }else{
                    url=url+keys[i]+"="+values[i];
                }
            }
        }
        Request request = new Request.Builder()
                .url(url).get().build();
        try {
            Response result = okHttpClient.newCall(request).execute();
            return result.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String postByOKHttp(String url){
        return postByOKHttp(url,null,null);
    }

    public String postByOKHttp(String url,String[] keys,String[] values){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (keys!=null){
            FormBody.Builder formBodyBuild = new FormBody.Builder();
            for (int i = 0; i <keys.length ; i++) {
                formBodyBuild.add(keys[i],values[i]);
            }
            requestBuilder.post(formBodyBuild.build());
        }
        try {
            Response result = okHttpClient.newCall(requestBuilder.build()).execute();
            return result.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getByVolley(){

    }
}
