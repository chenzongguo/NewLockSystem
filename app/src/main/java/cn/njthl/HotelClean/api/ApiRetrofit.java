package cn.njthl.HotelClean.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cn.njthl.HotelClean.api.base.BaseApiRetrofit;
import cn.njthl.HotelClean.model.Bean.UserPerfectBean;
import cn.njthl.HotelClean.model.request.BaseRequest;
import cn.njthl.HotelClean.model.request.ChangeStateRequest;
import cn.njthl.HotelClean.model.request.CheckCaptchaRequest;
import cn.njthl.HotelClean.model.request.CheckUpdateRequest;
import cn.njthl.HotelClean.model.request.CheckUserPhoneRequest;
import cn.njthl.HotelClean.model.request.GetCleanPicRequest;
import cn.njthl.HotelClean.model.request.GetOrderListRequest;
import cn.njthl.HotelClean.model.request.GetOrderRequest;
import cn.njthl.HotelClean.model.request.GetOrderRoomRatingRequest;
import cn.njthl.HotelClean.model.request.GetOrderStateNumRequest;
import cn.njthl.HotelClean.model.request.GetTokenRequest;
import cn.njthl.HotelClean.model.request.GetUserListRequest;
import cn.njthl.HotelClean.model.request.ParnterReceiptRequest;
import cn.njthl.HotelClean.model.request.SendCaptchaRequest;
import cn.njthl.HotelClean.model.request.UpdateOrderRoomStateRequest;
import cn.njthl.HotelClean.model.request.UserLoginRequest;
import cn.njthl.HotelClean.model.request.UserRegisterRequest;
import cn.njthl.HotelClean.model.request.UserTokenRequest;
import cn.njthl.HotelClean.model.response.BaseResponse;
import cn.njthl.HotelClean.model.response.CheckUpdateResponse;
import cn.njthl.HotelClean.model.response.GetCleanPicResponse;
import cn.njthl.HotelClean.model.response.GetOrderListResponse;
import cn.njthl.HotelClean.model.response.GetOrderResponse;
import cn.njthl.HotelClean.model.response.GetOrderRoomRatingResponse;
import cn.njthl.HotelClean.model.response.GetOrderStateNumResponse;
import cn.njthl.HotelClean.model.response.GetUserListResponse;
import cn.njthl.HotelClean.model.response.UserLoginResponse;
import cn.njthl.HotelClean.util.LogUtils;

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
    public Observable<BaseResponse> checkUserPhone( String phone) {
        return mApi.checkUserPhone(getRequestBody(new CheckUserPhoneRequest(phone)));
    }

    //用户注册
    public Observable<BaseResponse> userRegister( String phone, String pwd) {
        return mApi.userRegister(getRequestBody(new UserRegisterRequest(phone,pwd)));
    }

    //用户信息完善
    public Observable<BaseResponse> userPerfectData(UserPerfectBean userPerfectBean) {
        return mApi.userPerfectData(getUserTokenRequestBody(userPerfectBean));
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

    //商户接单
    public Observable<GetCleanPicResponse> getCleanPic(GetCleanPicRequest getCleanPicRequest) {
        return mApi.getCleanPic(getUserTokenRequestBody(getCleanPicRequest));
    }

    //商户接单
    public Observable<GetOrderRoomRatingResponse> getOrderRoomRating(GetOrderRoomRatingRequest getOrderRoomRatingRequest) {
        return mApi.getOrderRoomRating(getUserTokenRequestBody(getOrderRoomRatingRequest));
    }
}
