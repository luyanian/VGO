package com.lanhi.delivery.consignor.api;

import com.lanhi.delivery.consignor.api.response.BaseResponse;
import com.lanhi.delivery.consignor.api.response.GetCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStateCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStatesResponse;
import com.lanhi.delivery.consignor.api.response.GetVertificationResponse;
import com.lanhi.delivery.consignor.api.response.LoginResponse;
import com.lanhi.delivery.consignor.api.response.OrderDetailResponse;
import com.lanhi.delivery.consignor.api.response.OrderListResponse;
import com.lanhi.delivery.consignor.api.response.UploadFileResponse;
import com.lanhi.delivery.consignor.api.response.UserInfoResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService{

    @FormUrlEncoded
    @POST("appinterface/login.shtml")
    Observable<String> test(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/login.shtml")
    Observable<LoginResponse> login(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getverification.shtml")
    Observable<GetVertificationResponse> getVerification(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/register.shtml")
    Observable<BaseResponse> regist(@Field("str") String str);

    @POST("appinterface/getstate.shtml")
    Observable<GetStatesResponse> getStates();

    @FormUrlEncoded
    @POST("appinterface/getcity.shtml")
    Observable<GetCityResponse> getCity(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getstatecity.shtml")
    Observable<GetStateCityResponse> getStateCity(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/initpass.shtml")
    Observable<BaseResponse> resetPassword(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/updateuserinfo.shtml")
    Observable<BaseResponse> updateUserInfo(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/sendorders.shtml")
    Observable<BaseResponse> publishOrder(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/orderslist_s.shtml")
    Observable<OrderListResponse> getOderList(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/cancel_order.shtml")
    Observable<BaseResponse> cancleOrder(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/orderinfo.shtml")
    Observable<OrderDetailResponse> getOrderDetail(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getuserinfo.shtml")
    Observable<UserInfoResponse> getUserInfo(@Field("str") String str);

    @Headers({
            "Accept: application/json",
    })
    @Multipart
    @POST("appinterface/upload_user_img.shtml")
    Observable<UploadFileResponse> uploadUserImg(@Part MultipartBody.Part myFile, @Part MultipartBody.Part tokenid, @Part MultipartBody.Part userid);

    @Headers({
            "Accept: application/json",
    })
    @Multipart
    @POST("appinterface/upload_shop_img.shtml")
    Observable<UploadFileResponse> uploadShopImg(@Part MultipartBody.Part myFile, @Part MultipartBody.Part tokenid, @Part MultipartBody.Part userid);
}