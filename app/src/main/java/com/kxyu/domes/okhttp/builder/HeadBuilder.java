package com.kxyu.domes.okhttp.builder;

import com.kxyu.domes.okhttp.OkHttpUtils;
import com.kxyu.domes.okhttp.request.OtherRequest;
import com.kxyu.domes.okhttp.request.RequestCall;

/**
 * Created by mseven on 5/25/16.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
