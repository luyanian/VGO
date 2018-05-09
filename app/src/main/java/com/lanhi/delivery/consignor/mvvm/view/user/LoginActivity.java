package com.lanhi.delivery.consignor.mvvm.view.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lanhi.delivery.consignor.BR;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.ApiRepository;
import com.lanhi.delivery.consignor.api.response.GetVertificationResponse;
import com.lanhi.delivery.consignor.common.CountDownTimerUtils;
import com.lanhi.delivery.consignor.common.GlobalParams;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.common.RObserver;
import com.lanhi.delivery.consignor.common.SPKeys;
import com.lanhi.delivery.consignor.databinding.UserLoginActivityBinding;
import com.lanhi.delivery.consignor.api.response.LoginResponse;
import com.lanhi.delivery.consignor.mvvm.model.UserData;
import com.lanhi.delivery.consignor.mvvm.viewmodel.UserViewModel;
import com.lanhi.delivery.consignor.weight.selector.RSelectorChangeLisener;
import com.lanhi.ryon.utils.mutils.SPUtils;
import com.lanhi.ryon.utils.mutils.SpanUtils;

/**
 * Created by Administrator on 2018/3/21.
 */
@Route(path = "/user/login")
public class LoginActivity extends BaseActivity {
    private UserLoginActivityBinding binding;
    private UserViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_login_activity);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        binding.setData(viewModel.getLiveDate().getValue());

        binding.rselector.setRSelectorChangeListener(new RSelectorChangeLisener() {
            @Override
            public void onLeftClick() {
                viewModel.getLiveDate().getValue().setCurrentItem(0);
                binding.setData(viewModel.getLiveDate().getValue());
            }

            @Override
            public void onRightClick() {
                viewModel.getLiveDate().getValue().setCurrentItem(1);
                binding.setData(viewModel.getLiveDate().getValue());
            }
        });
        binding.setListener(new OnEventListener() {
            @Override
            public void login(View v) {
                super.login(v);
                viewModel.login(new RObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        SPUtils.getInstance().put(SPKeys.TOKENID,loginResponse.getTokenid());
                        LoginResponse.UserInfoData userInfoData = loginResponse.getData().get(0);
                        SPUtils.getInstance().saveObject(SPKeys.USER_INFO,userInfoData);
                        ARouter.getInstance().build("/main/main").navigation();
                    }
                });
            }

            @Override
            public void getVerCode(final View v) {
                super.getVerCode(v);
                viewModel.getVerification(new RObserver<GetVertificationResponse>() {
                    @Override
                    public void onSuccess(GetVertificationResponse getVertificationResponse) {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils((TextView) v, 60000, 1000);
                        mCountDownTimerUtils.start();
                    }
                }, GlobalParams.SCOPE.LOGIN_WITH_CODE);
            }

            @Override
            public void toRegistActivity(View v) {
                super.toRegistActivity(v);
                ARouter.getInstance().build("/user/register/step1").navigation();
            }

            @Override
            public void toForgotPwdActivity(View v) {
                super.toForgotPwdActivity(v);
                ARouter.getInstance().build("/user/password/forgot").navigation();
            }
        });
    }
}
