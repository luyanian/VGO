package com.lanhi.delivery.consignor.common;

import android.Manifest;
import android.view.View;

import com.lanhi.delivery.consignor.api.response.OrderListResponse;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

public class OnEventListener {
    public void login(View v){}
    public void signOut(View v){}
    public void getVerCode(View v){}
    public void vertifySMSCode(View v){};
    public void regist(View v){};
    public void toRegistActivity(View v){};
    public void toForgotPwdActivity(View v){};
    public void resetPassword(View v){};

    public void orderPublish(View v){}
    public void cancelOrderPublish(View v){}
    public void cancelOrder(View v, OrderListResponse.OrderListBean orderListBean){}
    public void viewOrderDetail(View v, OrderListResponse.OrderListBean orderListBean){}
    public void viewUserInfo(View v){}
    public void viewUserShop(View v){}
    public void viewUserAccunt(View v){}
    public void viewUserFinancial(View v){}
    public void viewUserMore(View v){}

    public void viewUpdateShopName(View v){}
    public void updateShopImg(View v){}
}
