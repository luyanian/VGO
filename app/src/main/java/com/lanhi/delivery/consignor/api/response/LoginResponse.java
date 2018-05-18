package com.lanhi.delivery.consignor.api.response;

import com.lanhi.delivery.consignor.api.response.bean.UserInfoDataBean;

import java.io.Serializable;
import java.util.List;

public class LoginResponse extends BaseResponse {

    private List<UserInfoDataBean> data;

    public List<UserInfoDataBean> getData() {
        return data;
    }

    public void setData(List<UserInfoDataBean> data) {
        this.data = data;
    }
}
