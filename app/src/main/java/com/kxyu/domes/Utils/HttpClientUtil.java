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


    private static String getXscreenListUrl() {
        return XSCREEN_URL + LIST_URL;
    }

    public static void useOkHttpPost(String jsonString, StringCallback callback) {
        OkHttpUtils
                .postString()
                .url(getXscreenListUrl())
                .content(jsonString)
                .build()
                .execute(callback);
    }
}
