package com.lanhi.vgo.api;

import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.GetCityResponse;
import com.lanhi.vgo.api.response.GetStateCityResponse;
import com.lanhi.vgo.api.response.GetStatesResponse;
import com.lanhi.vgo.api.response.GetVertificationResponse;
import com.lanhi.vgo.api.response.HotlineResponse;
import com.lanhi.vgo.api.response.OrderDetailResponse;
import com.lanhi.vgo.api.response.OrderListResponse;
import com.lanhi.vgo.api.response.UploadFileResponse;
import com.lanhi.vgo.api.response.UserInfoResponse;
import com.lanhi.vgo.api.response.WebInfoResponse;
import com.lanhi.vgo.common.Common;
import com.lanhi.vgo.api.response.LoginResponse;

import java.io.File;

import io.reactivex.Completable;
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
    public static Observable<UserInfoResponse> getUserInfo(String str) {
        Observable<UserInfoResponse> observable = ApiClient.getApiService().getUserInfo(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> editUserName(String str) {
        Observable<BaseResponse> observable = ApiClient.getApiService().editUserName(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> editUserAccountNum(String str) {
        Observable<BaseResponse> observable = ApiClient.getApiService().editUserAccountNum(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    public static Observable<BaseResponse> editUserPassword(String str) {
        Observable<BaseResponse> observable = ApiClient.getApiService().editUserPassword(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<HotlineResponse> getHotline(String str) {
        Observable<HotlineResponse> observable = ApiClient.getApiService().getHotline(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<WebInfoResponse> getAboutMeInfo(String str) {
        Observable<WebInfoResponse> observable = ApiClient.getApiService().getAboutMeInfo(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<WebInfoResponse> getAgreenmentInfo(String str) {
        Observable<WebInfoResponse> observable = ApiClient.getApiService().getAgreenmentInfo(Common.rsaEncrypt(str))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<UploadFileResponse> updateShopImg(String tokenid, String userid, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part myFilePart = MultipartBody.Part.createFormData("myFile", file.getName(), requestFile);
        MultipartBody.Part tokenidPart = MultipartBody.Part.createFormData("tokenid", tokenid);
        MultipartBody.Part useridPart = MultipartBody.Part.createFormData("userid", userid);

        Observable<UploadFileResponse> observable = ApiClient.getApiService().uploadShopImg(myFilePart,tokenidPart,useridPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<String> testDistanceMetrix() {
        Observable<String> observable = ApiClient.getApiService().testDistanceMetrix("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=天津市东丽区空港商务园&destinations=天津市东丽区华明镇润园&key=AIzaSyAXst1lQfAsaOfNs52zb8OaZcF7IsCw9E4")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}