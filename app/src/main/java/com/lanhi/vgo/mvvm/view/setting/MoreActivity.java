package com.lanhi.vgo.mvvm.view.setting;

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
import com.lanhi.vgo.BaseActivity;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.response.HotlineResponse;
import com.lanhi.vgo.common.OnEventListener;
import com.lanhi.vgo.databinding.SettingMoreActivityBinding;
import com.lanhi.vgo.mvvm.viewmodel.UserViewModel;
import com.lanhi.vgo.weight.titlebar.TitleBarOptions;
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
