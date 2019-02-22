package com.lanhi.vgo.mvvm.model;
import android.databinding.Bindable;
import android.databinding.BaseObservable;

import com.lanhi.vgo.BR;

public class OrderData extends BaseObservable {
    private String recipientName;
    private String recipientPhone;
    private String recipientZipCode;
    private String recipientAddress;
    private String recipientStateId;
    private String recipientStateName;
    private String recipientCityId;
    private String recipientCityName;
    private String consignorAddress;
    private String consignorStateId;
    private String consignorStateName;
    private String consignorCityId;
    private String consignorCityName;
    private String consignorZipCode;
    private String orderDesc;
    private String goodsAmount;
    private String postageFee;
    private String postageTip;
    private String remark;
    private String merchantid;
    private String consignorName;
    private String consignorPhone;
    private String timeValue;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipient) {
        this.recipientName = recipient;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientZipCode() {
        return recipientZipCode;
    }

    public void setRecipientZipCode(String recipientZipCode) {
        this.recipientZipCode = recipientZipCode;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientStateId() {
        return recipientStateId;
    }

    public void setRecipientStateId(String recipientStateId) {
        this.recipientStateId = recipientStateId;
    }

    public String getRecipientStateName() {
        return recipientStateName;
    }

    public void setRecipientStateName(String recipientStateName) {
        this.recipientStateName = recipientStateName;
    }

    public String getRecipientCityId() {
        return recipientCityId;
    }

    public void setRecipientCityId(String recipientCityId) {
        this.recipientCityId = recipientCityId;
    }

    public String getRecipientCityName() {
        return recipientCityName;
    }

    public void setRecipientCityName(String recipientCityName) {
        this.recipientCityName = recipientCityName;
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
    }

    public String getConsignorStateId() {
        return consignorStateId;
    }

    public void setConsignorStateId(String consignorStateId) {
        this.consignorStateId = consignorStateId;
    }

    public String getConsignorStateName() {
        return consignorStateName;
    }

    public void setConsignorStateName(String consignorStateName) {
        this.consignorStateName = consignorStateName;
    }

    public String getConsignorCityId() {
        return consignorCityId;
    }

    public void setConsignorCityId(String consignorCityId) {
        this.consignorCityId = consignorCityId;
    }

    public String getConsignorCityName() {
        return consignorCityName;
    }

    public void setConsignorCityName(String consignorCityName) {
        this.consignorCityName = consignorCityName;
    }

    public String getConsignorZipCode() {
        return consignorZipCode;
    }

    public void setConsignorZipCode(String consignorZipCode) {
        this.consignorZipCode = consignorZipCode;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    @Bindable
    public String getPostageFee() {
        return postageFee;
    }

    public void setPostageFee(String postageFee) {
        this.postageFee = postageFee;
        notifyPropertyChanged(BR.postageFee);
    }

    public String getPostageTip() {
        return postageTip;
    }

    public void setPostageTip(String postageTip) {
        this.postageTip = postageTip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsignorPhone() {
        return consignorPhone;
    }

    public void setConsignorPhone(String consignorPhone) {
        this.consignorPhone = consignorPhone;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }
}
