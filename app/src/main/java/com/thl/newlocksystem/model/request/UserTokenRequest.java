package com.thl.newlocksystem.model.request;

import com.thl.newlocksystem.app.AppConst;

public class UserTokenRequest {
    private String token;
    private String user_token;
    private String params;

    public UserTokenRequest(String token,String params){
        this.token = token;
        this.user_token = AppConst.USER_TOKEN;
        this.params = params;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


}
