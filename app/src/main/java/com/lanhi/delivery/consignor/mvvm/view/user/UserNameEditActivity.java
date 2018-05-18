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
import com.lanhi.delivery.consignor.databinding.UserNameEditActivityBinding;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
import com.lanhi.ryon.utils.mutils.ToastUtils;

@Route(path = "/user/name/edit")
public class UserNameEditActivity extends BaseActivity {
    private UserNameEditActivityBinding binding;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_name_edit_activity);
        userViewModel = ViewModelProviders.of(getInstance()).get(UserViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        UserData userData = new UserData();
        String shopeName = getIntent().getStringExtra("shopeName");
        userData.setShopName(shopeName);
        binding.setData(userData);
        binding.setEvent(new OnEventListener(){
            @Override
            public void userNameEdit(View v, String userName) {
                super.userNameEdit(v, userName);
                userViewModel.editUserName(userName, new RObserver<BaseResponse>() {
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
