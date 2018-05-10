package com.lanhi.delivery.consignor.mvvm.view.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lanhi.delivery.consignor.BaseActivity;
import com.lanhi.delivery.consignor.R;
import com.lanhi.delivery.consignor.common.OnEventListener;
import com.lanhi.delivery.consignor.databinding.UserShopNameEditActivityBinding;
import com.lanhi.delivery.consignor.mvvm.model.ShopData;
import com.lanhi.delivery.consignor.weight.titlebar.TitleBarOptions;
@Route(path = "/user/shop/name/edit")
public class ShopNameEditActivity extends BaseActivity {
    private UserShopNameEditActivityBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_shop_name_edit_activity);
        binding.titlebar.setTitleBarOptions(new TitleBarOptions(){
            @Override
            public void onLeftBack(View view) {
                super.onLeftBack(view);
                finish();
            }
        });
        ShopData shopData = new ShopData();
        String shopeName = getIntent().getStringExtra("shopeName");
        shopData.setShopName(shopeName);
        binding.setData(shopData);
        binding.setEvent(new OnEventListener(){
            @Override
            public void shopNameEdit(View v, String shopName) {
                super.shopNameEdit(v, shopName);
                // TODO: 2018/5/10  
            }
        });
    }
}
