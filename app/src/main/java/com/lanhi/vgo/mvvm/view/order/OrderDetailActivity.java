package com.lanhi.vgo.mvvm.view.order;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.ryon.utils.mutils.PhoneUtils;
import com.lanhi.vgo.BaseActivity;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.response.OrderDetailResponse;
import com.lanhi.vgo.common.OnEventListener;
import com.lanhi.vgo.databinding.OrderDetailActivityBinding;
import com.lanhi.vgo.mvvm.viewmodel.OrderViewModel;
import com.lanhi.vgo.weight.titlebar.TitleBarOptions;

@Route(path = "/order/detail")
public class OrderDetailActivity extends BaseActivity {
    private OrderDetailActivityBinding binding;
    private OrderViewModel orderViewModel;
    private String order_code;
    private OrderDetailResponse orderDetailResponse;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.order_detail_activity);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        order_code = getIntent().getStringExtra("order_code");
        orderViewModel.getOrderDetailLiveData().observe(this, new Observer<OrderDetailResponse>() {
            @Override
            public void onChanged(@Nullable OrderDetailResponse orderDetailResponse) {
                OrderDetailActivity.this.orderDetailResponse = orderDetailResponse;
                if(orderDetailResponse!=null&&orderDetailResponse.getData()!=null&&orderDetailResponse.getData().size()>0&&orderDetailResponse.getData().get(0)!=null){
                    binding.setData(orderDetailResponse.getData().get(0));
                }
            }
        });
        binding.setListener(new OnEventListener(){
            @Override
            public void callDelevery(View v, String phone) {
                super.callDelevery(v, phone);
                if(!TextUtils.isEmpty(phone)){
                    PhoneUtils.dial(phone);
                }
            }
        });
        orderViewModel.getOrderDetail(order_code);
    }
}
