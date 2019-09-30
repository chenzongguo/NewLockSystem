package com.thl.newlocksystem.model.request;

import com.thl.newlocksystem.util.SPUtils;
import com.thl.newlocksystem.util.TimeUtils;

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
