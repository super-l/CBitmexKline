package org.superl.exchange.CBitmexKline.utils;

/*
    exchange-spider
    Created by superl on 2018/5/21.
                                                                      00000     
                                                                      00000     
                                                                      00000     
      00000000    00000  00000  00000000000     00000000    000000000 00000     
     00000000000  00000  00000  000000000000   00000000000  000000000 00000     
     00000  000   00000  00000  000000 00000  000000 00000  00000000  00000     
     000000000    00000  00000  00000   0000  0000000000000 000000    00000     
      0000000000  00000  00000  00000   00000 0000000000000 00000     00000     
         0000000  00000  00000  00000   00000 00000         00000     00000     
     00000  0000  000000000000  000000000000  000000000000  00000     00000     
     00000000000  000000000000  000000000000   00000000000  00000     00000     
      000000000   0000000000    00000000000     00000000    00000     00000     
                                00000                                           
                                00000                 Team:bbs.nepenthes.cn       
                                00000                                           
*/

import lombok.extern.log4j.Log4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j
public class HttpUtil {

    static private OkHttpClient client;

    /**
     * post请求
     *
     * @param url      地址
     * @param params   参数
     * @return         请求结果
     */
    public static String doPost(String url, Map<String,String> params){
        FormBody.Builder formBody = makeParams(params);

        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        return  execute(request);
    }

    /**
     * GET请求，实现URL与参数分离
     *
     * @param url      请求地址
     * @param params   参数
     * @return         返回结果
     */
    public static String doGet(String url, Map<String,String> params){

        String data_params = "";
        String data_url = url;

        if(params.size()>0){
            data_params = generateParameters(params);
            data_url = url + data_params;
        }

        Request request = new Request.Builder()
                .url(data_url)
                .get()
                .build();
        return  execute(request);
    }

    private static String execute(Request request){
        try {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }else{
                log.error(response.body().string());
            }
        } catch (IOException e) {
            log.error("请求失败！提示信息："+e.getMessage());
        }
        return "";
    }

    private static FormBody.Builder makeParams(Map<String,String> params){
        FormBody.Builder formBody = new FormBody.Builder();
        Object[] keys = params.keySet().toArray();
        for(Object key : keys)
            formBody.add(key.toString(), params.get(key));

        return formBody;
    }

    public static String generateParameters(Map<String,String> parameters) {
        String urlAttachment = "";
        if(parameters.size()>0){
            urlAttachment = "?";

            Object[] keys = parameters.keySet().toArray();

            for(Object key : keys)
                urlAttachment += key.toString() + "=" + parameters.get(key) + "&";

            urlAttachment = urlAttachment.substring(0,urlAttachment.length()-1);
        }
        return urlAttachment;
    }
}
