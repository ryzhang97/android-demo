package com.match.library.utils.okhttp.builder;


import com.match.library.utils.okhttp.OkHttpUtils;
import com.match.library.utils.okhttp.request.OtherRequest;
import com.match.library.utils.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
