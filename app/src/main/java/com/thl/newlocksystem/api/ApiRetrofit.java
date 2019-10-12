package com.thl.newlocksystem.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thl.newlocksystem.api.base.BaseApiRetrofit;
import com.thl.newlocksystem.model.request.BaseRequest;
import com.thl.newlocksystem.model.request.ChangeStateRequest;
import com.thl.newlocksystem.model.request.CheckCaptchaRequest;
import com.thl.newlocksystem.model.request.CheckUpdateRequest;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.model.request.GetOrderRequest;
import com.thl.newlocksystem.model.request.GetOrderStateNumRequest;
import com.thl.newlocksystem.model.request.GetTokenRequest;
import com.thl.newlocksystem.model.request.GetUserListRequest;
import com.thl.newlocksystem.model.request.ParnterReceiptRequest;
import com.thl.newlocksystem.model.request.SendCaptchaRequest;
import com.thl.newlocksystem.model.request.UpdateOrderRoomStateRequest;
import com.thl.newlocksystem.model.request.UserLoginRequest;
import com.thl.newlocksystem.model.request.UserRegisterRequest;
import com.thl.newlocksystem.model.request.UserTokenRequest;
import com.thl.newlocksystem.model.response.BaseResponse;
import com.thl.newlocksystem.model.response.CheckUpdateResponse;
import com.thl.newlocksystem.model.response.GetOrderListResponse;
import com.thl.newlocksystem.model.response.GetOrderResponse;
import com.thl.newlocksystem.model.response.GetOrderStateNumResponse;
import com.thl.newlocksystem.model.response.GetUserListResponse;
import com.thl.newlocksystem.model.response.UserLoginResponse;
import com.thl.newlocksystem.util.LogUtils;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @创建者 CSDN_LQR
 * @描述 使用Retrofit对网络请求进行配置
 */
public class ApiRetrofit extends BaseApiRetrofit {

    public MyApi mApi;
    public static ApiRetrofit mInstance;

    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        mApi = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private RequestBody getRequestBody(Object obj) {
        String data = new Gson().toJson(obj);
        String route = new Gson().toJson(new BaseRequest("89CA7BFA98871245FF2B80F3167FB912",data));
        LogUtils.sf(route);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }

    private RequestBody getUserTokenRequestBody(Object obj) {
        String data = new Gson().toJson(obj);
        String route = new Gson().toJson(new UserTokenRequest("89CA7BFA98871245FF2B80F3167FB912",data));
        LogUtils.sf(route);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }




    //获取是否需要更新
    public Observable<CheckUpdateResponse> checkUpdate(String user_name, String id_number) {
        return mApi.checkUpdate(getRequestBody(new CheckUpdateRequest(user_name,id_number)));
    }

    //更改设备更新状态
    public Observable<CheckUpdateResponse> changeState(String imei, String update_status) {
        return mApi.changeState(getRequestBody(new ChangeStateRequest(imei,update_status)));
    }

    //获取token
    public Observable<BaseResponse> getToken(String appid) {
        return mApi.getToken(getRequestBody(new GetTokenRequest(appid)));
    }

    //获取验证码
    public Observable<BaseResponse> sendCaptcha( String phone) {
        return mApi.sendCaptcha(getRequestBody(new SendCaptchaRequest(phone)));
    }

    //验证验证码
    public Observable<BaseResponse> checkCaptcha( String phone, String captcha) {
        return mApi.checkCaptcha(getRequestBody(new CheckCaptchaRequest(phone,captcha)));
    }

    //用户注册
    public Observable<BaseResponse> userRegister( String phone, String pwd) {
        return mApi.userRegister(getRequestBody(new UserRegisterRequest(phone,pwd)));
    }

    //用户登录
    public Observable<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest) {
        return mApi.userLogin(getRequestBody(userLoginRequest));
    }

    //用户登录
    public Observable<GetUserListResponse> getUserList(GetUserListRequest getUserListRequest) {
        return mApi.getUserList(getUserTokenRequestBody(getUserListRequest));
    }
    //用户token验证登录
    public Observable<BaseResponse> checkUserToken() {
        return mApi.checkUserToken(getUserTokenRequestBody(""));
    }
    //商户信息完善

    //订单信息查询
    public Observable<GetOrderListResponse> getOrderList(GetOrderListRequest getOrderListRequest) {
        return mApi.getOrderList(getUserTokenRequestBody(getOrderListRequest));
    }

    //订单详情信息查询
    public Observable<GetOrderResponse> getOrder(GetOrderRequest getOrderRequest) {
        return mApi.getOrder(getUserTokenRequestBody(getOrderRequest));
    }

    //订单详情信息查询
    public Observable<GetOrderStateNumResponse> getOrderStateNum(GetOrderStateNumRequest getOrderStateNumRequest) {
        return mApi.getOrderStateNum(getUserTokenRequestBody(getOrderStateNumRequest));
    }

    //商户接单
    public Observable<BaseResponse> parnterReceipt(ParnterReceiptRequest parnterReceiptRequest) {
        return mApi.parnterReceipt(getUserTokenRequestBody(parnterReceiptRequest));
    }
    //商户接单
    public Observable<BaseResponse> updateOrderRoomState(UpdateOrderRoomStateRequest updateOrderRoomStateRequest) {
        return mApi.updateOrderRoomState(getUserTokenRequestBody(updateOrderRoomStateRequest));
    }
}
