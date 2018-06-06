package com.lanhi.vgo.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.vgo.BaseActivity;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.common.OnEventListener;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.databinding.UserPasswordEditActivityBinding;
import com.lanhi.vgo.mvvm.model.UserData;
import com.lanhi.vgo.mvvm.viewmodel.UserViewModel;
import com.lanhi.vgo.weight.titlebar.TitleBarOptions;
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
                        ToastUtils.showShort(R.string.msg_commit_successful);
                        finish();
                    }
                });
            }
        });
    }
}
