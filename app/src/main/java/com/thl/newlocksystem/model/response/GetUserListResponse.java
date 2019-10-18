package com.thl.newlocksystem.model.response;

import com.thl.newlocksystem.model.Bean.UserBean;

import java.util.List;

public class GetUserListResponse {

    private String code;

    private String errMessage;

    private List<UserBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public List<UserBean> getData() {
        return data;
    }

    public void setData(List<UserBean> data) {
        this.data = data;
    }
}
