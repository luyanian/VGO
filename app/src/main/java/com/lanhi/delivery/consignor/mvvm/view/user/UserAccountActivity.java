package com.lanhi.delivery.consignor.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.response.bean.UserInfoDataBean;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.common.SPKeys;
import com.lanhi.delivery.consignor.databinding.UserAccountManagermentActivityBinding;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
import com.lanhi.ryon.utils.mutils.SPUtils;

@Route(path = "/user/account/manage")
public class UserAccountActivity extends BaseActivity {
    private UserAccountManagermentActivityBinding binding;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_account_managerment_activity);
        userViewModel = ViewModelProviders.of(getInstance()).get(UserViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        UserInfoDataBean userInfoData = (UserInfoDataBean) SPUtils.getInstance().readObject(SPKeys.USER_INFO);
        if(userInfoData!=null){
//            userInfoData.
        }
        binding.setEvent(new OnEventListener(){
            @Override
            public void viewUserAccunt(View v) {
                super.viewUserAccunt(v);
                ARouter.getInstance().build("/user/name/edit").navigation();
            }

            @Override
            public void viewUserAccountInfo(View v) {
                super.viewUserAccountInfo(v);
                ARouter.getInstance().build("/user/check/edit").navigation();
            }

            @Override
            public void viewPasswordEdit(View v) {
                super.viewPasswordEdit(v);
                ARouter.getInstance().build("/user/password/edit").navigation();
            }
        });
    }
}
