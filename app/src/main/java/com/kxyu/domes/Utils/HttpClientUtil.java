package com.kxyu.domes.Utils;

import com.kxyu.domes.okhttp.OkHttpUtils;
import com.kxyu.domes.okhttp.callback.StringCallback;

/**
 * Created by kxyu on 16-8-2.
 */
public class HttpClientUtil {
    private static final String XSCREEN_URL = "http://newsapi.revanow.com/api/";
    private static final String LIST_URL = "102";
    private static final String CONTENT_TYPE = "application/json";
    public static final String DETAIL_URL = "articles";

    private static String getXscreenListUrl() {
        return XSCREEN_URL + LIST_URL;
    }

    public static void useOkHttpPost(String jsonString, StringCallback callback, String url ) {
        OkHttpUtils
                .postString()
                .url(XSCREEN_URL + url)
                .content(jsonString)
                .build()
                .execute(callback);
    }

    public static void useOkHttpPost(String jsonString, StringCallback callback ) {
        useOkHttpPost(jsonString, callback, LIST_URL);

    }

    public static void useOkHttpGet(String Url, StringCallback callback){

        OkHttpUtils.get().url(Url).build().execute(callback);

    }
}
