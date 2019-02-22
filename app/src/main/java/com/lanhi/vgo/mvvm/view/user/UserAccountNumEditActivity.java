package com.lanhi.vgo.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.ryon.utils.constant.SPConstants;
import com.lanhi.vgo.BaseActivity;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.bean.UserInfoDataBean;
import com.lanhi.vgo.common.OnEventListener;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.databinding.UserAccountNumEditActivityBinding;
import com.lanhi.vgo.mvvm.model.UserData;
import com.lanhi.vgo.mvvm.viewmodel.UserViewModel;
import com.lanhi.vgo.weight.titlebar.TitleBarOptions;
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
        UserInfoDataBean userInfo = (UserInfoDataBean) SPUtils.getInstance(SPConstants.USER.NAME).readObject(SPConstants.USER.USER_INFO);
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
