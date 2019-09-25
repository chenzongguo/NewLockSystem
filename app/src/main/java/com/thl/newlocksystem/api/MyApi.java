package com.thl.newlocksystem.api;

import com.thl.newlocksystem.model.response.CheckUpdateResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @创建者 CSDN_LQR
 * @描述 server端api
 */

public interface MyApi {

//    public static final String BASE_URL = "http://218.94.111.86:8091/";
//    public static final String BASE_URL = "http://192.168.20.190:8080/";
    public static final String BASE_URL = "http://192.168.20.190:8080/";


    //检查手机是否被注册
//    @POST("user/check_phone_available")
//    Observable<CheckPhoneResponse> checkPhoneAvailable(@Body RequestBody body);

    //获取是否需要更新
    @POST("LockUpdate/checkUpdate")
    Observable<CheckUpdateResponse> checkUpdate(@Body RequestBody jsonString);

    //更新设备的状态（1已下载，2已更新）
    @POST("LockUpdate/changeState")
    Observable<CheckUpdateResponse> changeState(@Body RequestBody jsonString);

    //获取短信验证码
    @POST("hsp/tool/sendCaptcha.do")
    Observable<CheckUpdateResponse> sendCaptcha(@Body RequestBody jsonString);
}
