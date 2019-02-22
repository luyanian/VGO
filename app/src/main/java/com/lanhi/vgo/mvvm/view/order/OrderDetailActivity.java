package com.lanhi.vgo.mvvm.view.order;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.ryon.utils.constant.SPConstants;
import com.lanhi.ryon.utils.mutils.PhoneUtils;
import com.lanhi.ryon.utils.mutils.SPUtils;
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
    private String locationStr = "";
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

            @Override
            public void onRightTextButton(View view) {
                super.onRightTextButton(view);
                String url = "https://www.google.com/maps/search/?api=1&query="+locationStr;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        order_code = getIntent().getStringExtra("order_code");
        orderViewModel.getOrderDetailLiveData().observe(this, new Observer<OrderDetailResponse>() {
            @Override
            public void onChanged(@Nullable OrderDetailResponse orderDetailResponse) {
                OrderDetailActivity.this.orderDetailResponse = orderDetailResponse;
                if(orderDetailResponse!=null&&orderDetailResponse.getData()!=null&&orderDetailResponse.getData().size()>0&&orderDetailResponse.getData().get(0)!=null){
                    binding.setData(orderDetailResponse.getData().get(0));

                    locationStr = "39.0636733,117.2749184";
//                    if(GlobalParams.ORDER_STATE.UNANSWEWD.equals(dataBean.getOrder_code())
//                            ||GlobalParams.ORDER_STATE.UNPICKUP.equals(dataBean.getOrder_code())){
//                        destination = dataBean.getF_stateinfo()+dataBean.getF_ctiyinfo()+dataBean.getF_addressinfo();
//                    }else{
//                        destination = dataBean.getS_stateinfo()+dataBean.getS_ctiyinfo()+dataBean.getS_addressinfo();
//                    }
                }
            }
        });
        binding.setListener(new OnEventListener(){
            @Override
            public void callPhone(View v, String phone) {
                super.callPhone(v, phone);
                if(!TextUtils.isEmpty(phone)){
                    PhoneUtils.dial(phone);
                }
            }
        });
        orderViewModel.getOrderDetail(order_code);
    }
}
