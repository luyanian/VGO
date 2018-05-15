package com.lanhi.delivery.consignor.mvvm.view.setting;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.response.HotlineResponse;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.databinding.SettingMoreActivityBinding;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
import com.lanhi.ryon.utils.mutils.PhoneUtils;

@Route(path = "/setting/more")
public class MoreActivity extends BaseActivity {
    private SettingMoreActivityBinding binding;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.setting_more_activity);
        userViewModel = ViewModelProviders.of(getInstance()).get(UserViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        binding.setEvent(new OnEventListener(){
            @Override
            public void onclickHotLine(View v,String phone) {
                super.onclickHotLine(v,phone);
                PhoneUtils.dial(phone);
            }
        });
        userViewModel.getHotLineMutableLiveData().observe(this, new Observer<HotlineResponse.DataBean>() {
            @Override
            public void onChanged(@Nullable HotlineResponse.DataBean dataBean) {
                if(dataBean!=null){
                    binding.setHotline(dataBean.getPhone());
                }
            }
        });
        userViewModel.getHotLine();
        userViewModel.getAboutMeInfo();

    }
}
