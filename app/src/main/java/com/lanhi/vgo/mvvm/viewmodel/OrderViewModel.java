package com.lanhi.vgo.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lanhi.ryon.utils.constant.SPConstants;
import com.lanhi.ryon.utils.mutils.RegexUtils;
import com.lanhi.ryon.utils.mutils.ToastUtils;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.ApiRepository;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.OrderDetailResponse;
import com.lanhi.vgo.api.response.OrderListResponse;
import com.lanhi.vgo.api.response.bean.UserInfoDataBean;
import com.lanhi.vgo.common.Common;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.mvvm.model.OrderData;
import com.lanhi.ryon.utils.mutils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderViewModel extends AndroidViewModel {

    private MutableLiveData<OrderData> orderPublishLiveData = new MutableLiveData<>();
    private MutableLiveData<List<OrderListResponse.OrderListBean>> orderListLiveData = new MutableLiveData<>();
    private MutableLiveData<OrderDetailResponse> orderDetailLiveData = new MutableLiveData<>();
    public OrderViewModel(@NonNull Application application) {
        super(application);
        OrderData orderData = new OrderData();
        UserInfoDataBean userInfoData = (UserInfoDataBean) SPUtils.getInstance(SPConstants.USER.NAME).readObject(SPConstants.USER.USER_INFO);
        if(userInfoData!=null){
            orderData.setConsignorName(userInfoData.getUser_name());
            orderData.setConsignorAddress(userInfoData.getAddressinfo());
            orderData.setConsignorCityId(userInfoData.getCtiyinfo());
            orderData.setConsignorCityName(userInfoData.getCity_name());
            orderData.setConsignorPhone(userInfoData.getAccount_number());
            orderData.setConsignorStateId(userInfoData.getStateinfo());
            orderData.setConsignorStateName(userInfoData.getState_name());
            orderData.setConsignorZipCode(userInfoData.getPostal_code());
            orderData.setMerchantid(userInfoData.getId());
        }
        orderPublishLiveData.setValue(orderData);
    }

    public MutableLiveData<OrderData> getOrderPublishLiveData() {
        return orderPublishLiveData;
    }

    public void orderPublish(RObserver<BaseResponse> rObserver) {
        OrderData orderData = getOrderPublishLiveData().getValue();
        if(TextUtils.isEmpty(orderData.getRecipientName())){
            ToastUtils.showShort(R.string.hint_input_recipient_name);
            return;
        }
        if(!RegexUtils.isMobileSimple(orderData.getRecipientPhone())){
            ToastUtils.showShort(R.string.hint_input_recipient_phone);
            return;
        }
        if(TextUtils.isEmpty(orderData.getRecipientZipCode())){
            ToastUtils.showShort(R.string.hint_input_recipient_zipCode);
            return;
        }
        if(TextUtils.isEmpty(orderData.getRecipientAddress())){
            ToastUtils.showShort(R.string.hint_input_recipient_address);
            return;
        }
        if(TextUtils.isEmpty(orderData.getRecipientStateId())){
            ToastUtils.showShort(R.string.hint_input_recipient_state);
            return;
        }
        if(TextUtils.isEmpty(orderData.getRecipientCityId())){
            ToastUtils.showShort(R.string.hint_input_recipient_city);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorName())){
            ToastUtils.showShort(R.string.hint_input_consignor_name);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorPhone())){
            ToastUtils.showShort(R.string.hint_input_consignor_phone);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorAddress())){
            ToastUtils.showShort(R.string.hint_input_consignor_address);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorStateId())){
            ToastUtils.showShort(R.string.hint_input_consignor_state);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorCityId())){
            ToastUtils.showShort(R.string.hint_input_consignor_city);
            return;
        }
        if(TextUtils.isEmpty(orderData.getConsignorZipCode())){
            ToastUtils.showShort(R.string.hint_input_consignor_zipCode);
            return;
        }
        if(TextUtils.isEmpty(orderData.getOrderDesc())){
            ToastUtils.showShort(R.string.hint_input_order_desc);
            return;
        }
        String goodsAmount = orderData.getGoodsAmount();
        if(!TextUtils.isEmpty(goodsAmount)&&goodsAmount.length()>1){
            goodsAmount = goodsAmount.substring(1);
        }else{
            ToastUtils.showShort(R.string.hint_input_order_amount);
            return;
        }
        String postageFee =orderData.getPostageFee();
        if(!TextUtils.isEmpty(postageFee)&&postageFee.length()>1){
            postageFee = postageFee.substring(1);
        }else{
            ToastUtils.showShort(R.string.hint_input_postage_fee);
            return;
        }
        String postageTip = orderData.getPostageTip();
        if(!TextUtils.isEmpty(postageTip)&&postageTip.length()>1){
            postageTip = postageTip.substring(1);
        }else{
            postageTip="";
        }
        Map map = new HashMap();
        map.put("tokenid", Common.getToken());
        map.put("recipient",orderData.getRecipientName());
        map.put("recipientPhone",orderData.getRecipientPhone());
        map.put("recipientZipCode",orderData.getRecipientZipCode());
        map.put("recipientAddress",orderData.getRecipientAddress());
        map.put("recipientState",orderData.getRecipientStateId());
        map.put("recipientCity",orderData.getRecipientCityId());
        map.put("consignor",orderData.getConsignorName());
        map.put("consignorPhone",orderData.getConsignorPhone());
        map.put("consignorAddress",orderData.getConsignorAddress());
        map.put("consignorState",orderData.getConsignorStateId());
        map.put("consignorCity",orderData.getConsignorCityId());
        map.put("consignorZipCode",orderData.getConsignorZipCode());
        map.put("orderDesc",orderData.getOrderDesc());
        map.put("goodsAmount",goodsAmount);
        map.put("postageFee",postageFee);
        map.put("postageTip",postageTip);
        map.put("remark",orderData.getRemark());
        map.put("merchantid",orderData.getMerchantid());
        ApiRepository.publishOrder(new Gson().toJson(map)).subscribe(rObserver);

    }

    public void clearInputInfo() {
        OrderData orderData = new OrderData();
        UserInfoDataBean userInfoData = (UserInfoDataBean) SPUtils.getInstance(SPConstants.USER.NAME).readObject(SPConstants.USER.USER_INFO);
        if(userInfoData!=null){
            orderData.setConsignorName(userInfoData.getUser_name());
            orderData.setConsignorAddress(userInfoData.getAddressinfo());
            orderData.setConsignorCityId(userInfoData.getCtiyinfo());
            orderData.setConsignorCityName(userInfoData.getCity_name());
            orderData.setConsignorPhone(userInfoData.getAccount_number());
            orderData.setConsignorStateId(userInfoData.getStateinfo());
            orderData.setConsignorStateName(userInfoData.getState_name());
            orderData.setConsignorZipCode(userInfoData.getPostal_code());
            orderData.setMerchantid(userInfoData.getId());
            orderPublishLiveData.setValue(orderData);
        }
    }

    public MutableLiveData<List<OrderListResponse.OrderListBean>> getOrderListLiveData(){
        return orderListLiveData;
    }

    public synchronized void loadOrderList(final String order_state, final int pagenum) {
        Map map = new HashMap();
        UserInfoDataBean userInfoData = (UserInfoDataBean) SPUtils.getInstance(SPConstants.USER.NAME).readObject(SPConstants.USER.USER_INFO);
        if(userInfoData!=null){
            map.put("merchantid",userInfoData.getId());
            map.put("order_state",order_state);
            map.put("tokenid", Common.getToken());
            map.put("pagenum",pagenum+"");
            map.put("pagecount",10+"");
            ApiRepository.getOrderList(new Gson().toJson(map)).subscribe(new RObserver<OrderListResponse>() {
                @Override
                public void onSuccess(OrderListResponse orderListResponse) {
                    if(orderListResponse.getData()!=null){
                        if(pagenum<=1){
                            orderListLiveData.setValue(orderListResponse.getData());
                        }else {
                            List<OrderListResponse.OrderListBean> temp = orderListLiveData.getValue();
                            temp.addAll(orderListResponse.getData());
                            orderListLiveData.setValue(temp);
                        }
                    }else{
                        List<OrderListResponse.OrderListBean> temp = orderListLiveData.getValue();
                        temp.clear();
                        orderListLiveData.setValue(temp);
                    }
                }
            });
        }
    }

    public void cancelOrderPublish(OrderListResponse.OrderListBean user,RObserver<BaseResponse> rObserver) {
        if(user==null){
            return;
        }
        Map map = new HashMap();
        map.put("tokenid", Common.getToken());
        map.put("ordercode",user.getOrder_code());
        ApiRepository.cancelOrder(new Gson().toJson(map)).subscribe(rObserver);

    }

    public void getOrderDetail(String order_code) {
        Map map = new HashMap();
        map.put("tokenid", Common.getToken());
        map.put("ordercode",order_code);

        ApiRepository.getOrderDetail(new Gson().toJson(map)).subscribe(new RObserver<OrderDetailResponse>() {
            @Override
            public void onSuccess(OrderDetailResponse orderDetailResponse) {
                orderDetailLiveData.setValue(orderDetailResponse);
            }
        });
    }

    public MutableLiveData<OrderDetailResponse> getOrderDetailLiveData(){
        return orderDetailLiveData;
    }
}
