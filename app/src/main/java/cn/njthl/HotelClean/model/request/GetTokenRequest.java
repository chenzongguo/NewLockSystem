package cn.njthl.HotelClean.model.request;

import cn.njthl.HotelClean.util.SPUtils;
import cn.njthl.HotelClean.util.TimeUtils;

public class GetTokenRequest {
    private String appid;

    private String time;

    private String ciphertext;

    public GetTokenRequest(String appid) {
        this.appid = appid;
        this.time = TimeUtils.getDateTime16();
        this.ciphertext = SPUtils.MD5(appid+time);
    }
}
