package com.lanhi.vgo.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.lanhi.ryon.utils.mutils.LogUtils;
import com.lanhi.ryon.utils.mutils.SPUtils;
import com.lanhi.vgo.api.ApiRepository;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.bean.UserInfoDataBean;
import com.lanhi.vgo.common.Common;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.common.SPKeys;

import java.util.HashMap;
import java.util.Map;

/**
 * 一项继承 FirebaseInstanceIdService 的服务，
 * 用于处理注册令牌的创建、轮替和更新。如果要发送至特定设备或者创建设备组，则必须添加此服务。
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Get updated InstanceID token.
    }
}
