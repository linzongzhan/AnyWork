package com.qgstudio.anywork.notice;

import com.google.gson.JsonObject;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.User;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface NoticeApi {
    @POST("message/show")
    @Headers("Content-Type:application/json")
    Observable<ResponseResult<JsonObject>> getNotice(@Body Object object);
}
