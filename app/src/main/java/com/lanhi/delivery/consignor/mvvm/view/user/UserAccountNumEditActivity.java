package com.lanhi.delivery.consignor.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.response.BaseResponse;
import com.lanhi.delivery.consignor.api.response.LoginResponse;
import com.lanhi.delivery.consignor.api.response.bean.UserInfoDataBean;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.common.RObserver;
import com.lanhi.delivery.consignor.common.SPKeys;
import com.lanhi.delivery.consignor.databinding.UserAccountNumEditActivityBinding;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
import com.lanhi.ryon.utils.mutils.SPUtils;
import com.lanhi.ryon.utils.mutils.ToastUtils;

@Route(path = "/user/check/edit")
public class UserAccountNumEditActivity extends BaseActivity {
    private UserAccountNumEditActivityBinding binding;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_account_num_edit_activity);
        userViewModel = ViewModelProviders.of(getInstance()).get(UserViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        UserInfoDataBean userInfo = (UserInfoDataBean) SPUtils.getInstance().readObject(SPKeys.USER_INFO);
        UserData userData = new UserData();
        if(userInfo!=null){
            userData.setCheckNum(userInfo.getChecking_account());
            userData.setRoutingNum(userInfo.getRouting_number());
        }
        binding.setData(userData);
        binding.setEvent(new OnEventListener(){
            @Override
            public void userAccountNumEdit(View v, String checkNum, String routingNum) {
                super.userAccountNumEdit(v, checkNum, routingNum);
                userViewModel.editUserAccountNum(checkNum, routingNum, new RObserver<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ToastUtils.showShort(R.string.msg_commit_successful);
                        finish();
                    }
                });
            }
        });
    }
}
