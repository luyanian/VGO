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
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.common.RObserver;
import com.lanhi.delivery.consignor.databinding.UserPasswordEditActivityBinding;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
import com.lanhi.ryon.utils.mutils.ToastUtils;
@Route(path = "/user/password/edit")
public class UserPasswordEditActivity extends BaseActivity {
    UserPasswordEditActivityBinding binding;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_password_edit_activity);
        userViewModel = ViewModelProviders.of(getInstance()).get(UserViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        UserData userData = new UserData();
        binding.setData(userData);
        binding.setListenser(new OnEventListener(){
            @Override
            public void editPassword(View v, String oldPassword, String newPassword, String newPassword2) {
                super.editPassword(v, oldPassword, newPassword, newPassword2);
                userViewModel.editUserPassword(oldPassword, newPassword, newPassword2, new RObserver<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ToastUtils.showShort(baseResponse.getMsg());
                        finish();
                    }
                });
            }
        });
    }
}
