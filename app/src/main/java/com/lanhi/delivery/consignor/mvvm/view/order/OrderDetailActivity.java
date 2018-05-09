package com.lanhi.delivery.consignor.mvvm.view.order;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.api.response.OrderDetailResponse;
import com.lanhi.delivery.consignor.databinding.OrderDetailActivityBinding;
import com.lanhi.delivery.consignor.mvvm.viewmodel.OrderViewModel;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;

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
                binding.setData(orderDetailResponse);
            }
        });
        orderViewModel.getOrderDetail(order_code);
    }
}
