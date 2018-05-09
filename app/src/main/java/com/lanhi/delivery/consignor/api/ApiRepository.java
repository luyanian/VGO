package com.lanhi.delivery.consignor.api;

import com.lanhi.delivery.consignor.api.response.BaseResponse;
import com.lanhi.delivery.consignor.api.response.GetCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStateCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStatesResponse;
import com.lanhi.delivery.consignor.api.response.GetVertificationResponse;
import com.lanhi.delivery.consignor.api.response.OrderDetailResponse;
import com.lanhi.delivery.consignor.api.response.OrderListResponse;
import com.lanhi.delivery.consignor.common.Common;
import com.lanhi.delivery.consignor.api.response.LoginResponse;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ApiRepository {

    public static Observable<String> test(String str){
        Observable<String> observable = ApiClient.getApiService().test(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<LoginResponse> login(String str){
        Observable<LoginResponse> observable = ApiClient.getApiService().login(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<BaseResponse> regist(String str){
        Observable<BaseResponse> observable = ApiClient.getApiService().regist(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<GetStatesResponse> getStates(){
        Observable<GetStatesResponse> observable = ApiClient.getApiService().getStates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<GetCityResponse> getCitys(String str){
        Observable<GetCityResponse> observable = ApiClient.getApiService().getCity(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<GetStateCityResponse> getStateCity(String str){
        Observable<GetStateCityResponse> observable = ApiClient.getApiService().getStateCity(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> resetPassword(String str){
        Observable<BaseResponse> observable = ApiClient.getApiService().resetPassword(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> updateUserInfo(String str){
        Observable<BaseResponse> observable = ApiClient.getApiService().updateUserInfo(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<GetVertificationResponse> getVerification(String str){
        Observable<GetVertificationResponse> observable = ApiClient.getApiService().getVerification(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> publishOrder(String str){
        Observable<BaseResponse> observable = ApiClient.getApiService().publishOrder(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<OrderListResponse> getOrderList(String str){
        Observable<OrderListResponse> observable = ApiClient.getApiService().getOderList(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<BaseResponse> cancelOrder(String str) {
        Observable<BaseResponse> observable = ApiClient.getApiService().cancleOrder(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<OrderDetailResponse> getOrderDetail(String str) {
        Observable<OrderDetailResponse> observable = ApiClient.getApiService().getOrderDetail(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<BaseResponse> updateShopImg(String tokenid, String userid, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", file.getName(), requestFile);
        Observable<BaseResponse> observable = ApiClient.getApiService().uploadShopImg(tokenid,userid,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}