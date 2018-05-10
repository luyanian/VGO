package com.lanhi.delivery.consignor.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lanhi.delivery.consignor.App;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.ApiRepository;
import com.lanhi.delivery.consignor.api.response.BaseResponse;
import com.lanhi.delivery.consignor.api.response.GetCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStateCityResponse;
import com.lanhi.delivery.consignor.api.response.GetStatesResponse;
import com.lanhi.delivery.consignor.api.response.GetVertificationResponse;
import com.lanhi.delivery.consignor.api.response.LoginResponse;
import com.lanhi.delivery.consignor.api.response.UploadFileResponse;
import com.lanhi.delivery.consignor.api.response.UserInfoResponse;
import com.lanhi.delivery.consignor.common.Common;
import com.lanhi.delivery.consignor.common.GlobalParams;
import com.lanhi.delivery.consignor.common.RObserver;
import com.lanhi.delivery.consignor.common.SPKeys;
import com.lanhi.delivery.consignor.mvvm.model.StateCityData;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.ryon.utils.mutils.RegexUtils;
import com.lanhi.ryon.utils.mutils.SPUtils;
import com.lanhi.ryon.utils.mutils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.lanhi.delivery.consignor.common.GlobalParams.USER_TYPE;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<UserData> mutableLiveData = new MutableLiveData<>();
    private static MutableLiveData<UserInfoResponse> userInfoResponseMutableLiveData = new MutableLiveData<>();
    public UserViewModel(@NonNull Application application) {
        super(application);
        UserData userData = new UserData();
        getLiveDate().setValue(userData);
    }
    public MutableLiveData<UserData> getLiveDate(){
        return mutableLiveData;
    }
    public MutableLiveData<UserInfoResponse> getUserInfoMutableLiveData(){
        return userInfoResponseMutableLiveData;
    }

    public void getVerification(RObserver<GetVertificationResponse> rObserver,String scop) {
        UserData registData = getLiveDate().getValue();
        if(TextUtils.isEmpty(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_mobile));
            return;
        }
        if(!RegexUtils.isESMobile(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_format_mobile));
            return;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("phone",registData.getPhone());
        map.put("scope", scop);
        map.put("userType",USER_TYPE);
        String json = new Gson().toJson(map);
        ApiRepository.getVerification(json).subscribe(rObserver);
    }

    public void vertifyCode(String apiSmsCode, RObserver<BaseResponse> rObserver) {
        UserData registData = getLiveDate().getValue();
        if(TextUtils.isEmpty(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_mobile));
            return;
        }
        if(!RegexUtils.isESMobile(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_format_mobile));
            return;
        }
        if(TextUtils.isEmpty(registData.getSmsCode())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_verCode));
            return;
        }
        if(!apiSmsCode.equals(registData.getSmsCode())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_faild_verCode));
            return;
        }
        if(TextUtils.isEmpty(registData.getPassword())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_password));
            return;
        }
        if(TextUtils.isEmpty(registData.getPassword2())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_password2));
            return;
        }
        if(!registData.getPassword().equals(registData.getPassword2())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_ensure_password));
            return;
        }
        rObserver.onSuccess(new BaseResponse());
    }

    public void regist(StateCityData stateCityData,RObserver<BaseResponse> rObserver) {
        UserData registData = getLiveDate().getValue();
        if(TextUtils.isEmpty(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_mobile));
            return;
        }
        if(!RegexUtils.isESMobile(registData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_format_mobile));
            return;
        }
        if(TextUtils.isEmpty(registData.getSmsCode())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_verCode));
            return;
        }
        if(TextUtils.isEmpty(registData.getPassword())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_password));
            return;
        }
        if(TextUtils.isEmpty(registData.getPassword2())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_empty_password2));
            return;
        }
        if(!registData.getPassword2().equals(registData.getPassword())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_ensure_password));
            return;
        }
        // TODO: 2018/4/2 注册用户 输入信息验证
        Map map = new HashMap();
        map.put("phone",registData.getPhone());
        map.put("userType",GlobalParams.USER_TYPE);
        map.put("smsCode",registData.getSmsCode());
        map.put("password",registData.getPassword());
        map.put("userName",registData.getUserName());
        map.put("merchantName",registData.getShopName());
        map.put("zipCode",registData.getZipCode());
        map.put("state",stateCityData.getStateId());
        map.put("city",stateCityData.getId());
        map.put("address",registData.getAddress());
        map.put("email",registData.getEmail());
        map.put("checkingAccount",registData.getCheckNum());
        map.put("routingNum",registData.getRoutingNum());
        map.put("dutyNum",registData.getTaxNum());
        map.put("refereeName",registData.getRefereeName());
        map.put("refereePhone",registData.getRefereeTel());
        ApiRepository.regist(new Gson().toJson(map)).subscribe(rObserver);
    }

    public void login(RObserver<LoginResponse> observer) {
        UserData userData = getLiveDate().getValue();
        if(!RegexUtils.isESMobile(userData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_format_mobile));
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("phone",userData.getPhone());
        map.put("method",userData.getCurrentItem()==0? GlobalParams.SIGNIN_TYPE.SIGNIN_WITH_PASSWORD: GlobalParams.SIGNIN_TYPE.SIGNIN_WITH_VERTIFY_CODE);
        map.put("password",userData.getPassword());
        map.put("smsCode",userData.getSmsCode());
        String json = new Gson().toJson(map);
        ApiRepository.login(json).subscribe(observer);
    }

    public void resetPassword(RObserver<BaseResponse> observer){
        UserData userData = getLiveDate().getValue();
        if(!RegexUtils.isESMobile(userData.getPhone())){
            ToastUtils.showShort(App.getInstance().getResources().getString(R.string.error_format_mobile));
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("phone",userData.getPhone());
        map.put("password",userData.getPassword());
        map.put("smsCode",userData.getSmsCode());
        String json = new Gson().toJson(map);
        ApiRepository.resetPassword(json).subscribe(observer);
    }
    /**
     * 根据邮编或者州和城市
     * @param zip_code
     * @param observer
     */
    public void getStateCityWithZipCode(String zip_code,RObserver<GetStateCityResponse> observer){
        Map map = new HashMap();
        map.put("zip_code",zip_code);
        String json = new Gson().toJson(map);
        ApiRepository.getStateCity(json).subscribe(observer);
    }

    public void loadUserInfo(){
        LoginResponse.UserInfoData userInfoData = (LoginResponse.UserInfoData) SPUtils.getInstance().readObject(SPKeys.USER_INFO);
        if(userInfoData!=null){
            Map map = new HashMap();
            map.put("tokenid",Common.getToken());
            map.put("userid",userInfoData.getId());
            String json = new Gson().toJson(map);
            ApiRepository.getUserInfo(json).subscribe(new RObserver<UserInfoResponse>() {
                @Override
                public void onSuccess(UserInfoResponse userInfoResponse) {
                    userInfoResponseMutableLiveData.setValue(userInfoResponse);
                }
            });
        }
    }

    public void updateShopImg(String filePath, RObserver<UploadFileResponse> rObserver) {
        File file = new File(filePath);
        LoginResponse.UserInfoData userInfoData = (LoginResponse.UserInfoData) SPUtils.getInstance().readObject(SPKeys.USER_INFO);
        if(userInfoData!=null){
            ApiRepository.updateShopImg(Common.getToken(),userInfoData.getId(),file).subscribe(rObserver);
        }
    }
}
