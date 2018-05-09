package com.lanhi.delivery.consignor.mvvm.view.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.delivery.consignor.App;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.response.BaseResponse;
import com.lanhi.delivery.consignor.api.response.GetVertificationResponse;
import com.lanhi.delivery.consignor.common.GlobalParams;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.common.RObserver;
import com.lanhi.delivery.consignor.databinding.UserFindPasswordBinding;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;

@Route(path = "/user/password/forgot")
public class ForgotPasswordActivity extends BaseActivity {
    UserFindPasswordBinding binding;
    UserViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.user_find_password);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initTitleBar();
        initEventListener();
        initDataChanged();
        initData();
    }

    private void initTitleBar() {
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
    }

    private void initEventListener() {
        binding.setListener(new OnEventListener(){
            @Override
            public void resetPassword(final View v) {
                super.getVerCode(v);
                if(viewModel.getLiveDate().getValue().getCurrentItem()==0) {
                    viewModel.getVerification(new RObserver<GetVertificationResponse>() {
                        @Override
                        public void onSuccess(GetVertificationResponse getVertificationResponse) {
                            viewModel.getLiveDate().getValue().setCurrentItem(1);
                            viewModel.getLiveDate().getValue().setMsgTip(App.getInstance().getResources().getString(R.string.msg_vertify_account_success));
                            binding.setData(viewModel.getLiveDate().getValue());
                        }
                    }, GlobalParams.SCOPE.FIND_PASSWORD);
                }else{
                    viewModel.resetPassword(new RObserver<BaseResponse>() {
                        @Override
                        public void onSuccess(BaseResponse baseResponse) {
                            finish();
                        }
                    });
                }
            }
        });
    }

    private void initDataChanged() {
        viewModel.getLiveDate().observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(@Nullable UserData userData) {
                if(!TextUtils.isEmpty(userData.getMsgTip())) {
                    binding.setMsg(userData.getMsgTip());
                }
            }
        });
    }

    private void initData() {
        binding.setData(viewModel.getLiveDate().getValue());
        viewModel.getLiveDate().getValue().setCurrentItem(0);
        viewModel.getLiveDate().getValue().setMsgTip(App.getInstance().getResources().getString(R.string.msg_vertify_your_account));
    }
}
