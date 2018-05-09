package com.lanhi.delivery.consignor.api.response;

import java.io.Serializable;
import java.util.List;

public class LoginResponse extends BaseResponse {

    private List<UserInfoData> data;

    public List<UserInfoData> getData() {
        return data;
    }

    public void setData(List<UserInfoData> data) {
        this.data = data;
    }

    public static class UserInfoData implements Serializable{
        /**
         * img_url : uploads/4700693b-f04b-4666-8842-0027d07a9ec8.zip
         * routing_number : 0
         * ctiyinfo : 1
         * account_number : 111
         * user_type : consignor
         * remarks :
         * shop_head_portrait : null
         * loginpass : 96E79218965EB72C92A549DD5A330112
         * referee_name :
         * account_state : 1
         * shop_name : 333
         * createtime : null
         * sortinfo : 13
         * id : 2
         * user_name : 222
         * addressinfo : eeee
         * shop_duty_paragraph :
         * checking_account :
         * emailinfo : 555
         * reasons_for_rejection :
         * postal_code :
         * referee_tel :
         * stateinfo : 1
         * social_security :
         * online_state : null
         */

        private String img_url;
        private String routing_number;
        private String ctiyinfo;
        private String account_number;
        private String user_type;
        private String remarks;
        private Object shop_head_portrait;
        private String loginpass;
        private String referee_name;
        private String account_state;
        private String shop_name;
        private Object createtime;
        private String sortinfo;
        private String id;
        private String user_name;
        private String addressinfo;
        private String shop_duty_paragraph;
        private String checking_account;
        private String emailinfo;
        private String reasons_for_rejection;
        private String postal_code;
        private String referee_tel;
        private String stateinfo;
        private String social_security;
        private Object online_state;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getRouting_number() {
            return routing_number;
        }

        public void setRouting_number(String routing_number) {
            this.routing_number = routing_number;
        }

        public String getCtiyinfo() {
            return ctiyinfo;
        }

        public void setCtiyinfo(String ctiyinfo) {
            this.ctiyinfo = ctiyinfo;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Object getShop_head_portrait() {
            return shop_head_portrait;
        }

        public void setShop_head_portrait(Object shop_head_portrait) {
            this.shop_head_portrait = shop_head_portrait;
        }

        public String getLoginpass() {
            return loginpass;
        }

        public void setLoginpass(String loginpass) {
            this.loginpass = loginpass;
        }

        public String getReferee_name() {
            return referee_name;
        }

        public void setReferee_name(String referee_name) {
            this.referee_name = referee_name;
        }

        public String getAccount_state() {
            return account_state;
        }

        public void setAccount_state(String account_state) {
            this.account_state = account_state;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public String getSortinfo() {
            return sortinfo;
        }

        public void setSortinfo(String sortinfo) {
            this.sortinfo = sortinfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(String addressinfo) {
            this.addressinfo = addressinfo;
        }

        public String getShop_duty_paragraph() {
            return shop_duty_paragraph;
        }

        public void setShop_duty_paragraph(String shop_duty_paragraph) {
            this.shop_duty_paragraph = shop_duty_paragraph;
        }

        public String getChecking_account() {
            return checking_account;
        }

        public void setChecking_account(String checking_account) {
            this.checking_account = checking_account;
        }

        public String getEmailinfo() {
            return emailinfo;
        }

        public void setEmailinfo(String emailinfo) {
            this.emailinfo = emailinfo;
        }

        public String getReasons_for_rejection() {
            return reasons_for_rejection;
        }

        public void setReasons_for_rejection(String reasons_for_rejection) {
            this.reasons_for_rejection = reasons_for_rejection;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }

        public String getReferee_tel() {
            return referee_tel;
        }

        public void setReferee_tel(String referee_tel) {
            this.referee_tel = referee_tel;
        }

        public String getStateinfo() {
            return stateinfo;
        }

        public void setStateinfo(String stateinfo) {
            this.stateinfo = stateinfo;
        }

        public String getSocial_security() {
            return social_security;
        }

        public void setSocial_security(String social_security) {
            this.social_security = social_security;
        }

        public Object getOnline_state() {
            return online_state;
        }

        public void setOnline_state(Object online_state) {
            this.online_state = online_state;
        }
    }
}
