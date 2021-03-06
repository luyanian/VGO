package com.lanhi.vgo.api;

import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.DistanceFeeResponse;
import com.lanhi.vgo.api.response.DistanceMatrixResponse;
import com.lanhi.vgo.api.response.GetCityResponse;
import com.lanhi.vgo.api.response.GetStateCityResponse;
import com.lanhi.vgo.api.response.GetStatesResponse;
import com.lanhi.vgo.api.response.GetVertificationResponse;
import com.lanhi.vgo.api.response.HotlineResponse;
import com.lanhi.vgo.api.response.LoginResponse;
import com.lanhi.vgo.api.response.OrderDetailResponse;
import com.lanhi.vgo.api.response.OrderListResponse;
import com.lanhi.vgo.api.response.ServiceScopeResponse;
import com.lanhi.vgo.api.response.UploadFileResponse;
import com.lanhi.vgo.api.response.UserBillsResponse;
import com.lanhi.vgo.api.response.UserInfoResponse;
import com.lanhi.vgo.api.response.WebInfoResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

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

    @FormUrlEncoded
    @POST("appinterface/update_username.shtml")
    Observable<BaseResponse> editUserName(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/update_user_num.shtml")
    Observable<BaseResponse> editUserAccountNum(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/user_update_pass.shtml")
    Observable<BaseResponse> editUserPassword(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/gethotline.shtml")
    Observable<HotlineResponse> getHotline(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getapp_introduce.shtml")
    Observable<WebInfoResponse> getAboutMeInfo(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getagreement.shtml")
    Observable<WebInfoResponse> getAgreenmentInfo(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/editUserAppToken.shtml")
    Observable<BaseResponse> getUpdateFMCToken(@Field("str") String str);

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

    @GET()
    Observable<DistanceMatrixResponse> getDistanceMetrix(@Url String str);

    @FormUrlEncoded
    @POST("appinterface/distribution_fee.shtml")
    Observable<DistanceFeeResponse> getDistanceFee(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/update_min_average.shtml")
    Observable<ServiceScopeResponse> updateServiceScope(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getmin_average.shtml")
    Observable<ServiceScopeResponse> getServiceScope(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/getAverage.shtml")
    Observable<ServiceScopeResponse> getUserScore(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/appinterface/bEvaluateList_m.shtml")
    Observable<ServiceScopeResponse> getUserScoreRecord(@Field("str") String str);

    @FormUrlEncoded
    @POST("appinterface/my_bill.shtml")
    Observable<UserBillsResponse> getUserBills(@Field("str") String str);
}