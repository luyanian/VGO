package com.lanhi.vgo.mvvm.view;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.lanhi.ryon.utils.mutils.LogUtils;
import com.lanhi.ryon.utils.mutils.SPUtils;
import com.lanhi.vgo.BaseActivity;
import com.lanhi.vgo.R;
import com.lanhi.vgo.api.ApiRepository;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.bean.UserInfoDataBean;
import com.lanhi.vgo.common.Common;
import com.lanhi.vgo.common.OnMenuSelectorListener;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.common.SPKeys;
import com.lanhi.vgo.databinding.MainActivityBinding;
import com.lanhi.vgo.mvvm.view.order.OrderListFragment;
import com.lanhi.vgo.mvvm.view.order.OrderPublishFragment;
import com.lanhi.vgo.mvvm.view.user.UserFragment;

import java.util.HashMap;
import java.util.Map;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity {
    MainActivityBinding binding;
    private FragmentManager fragmentManager;
    private Fragment orderPushlishFragment;
    private Fragment orderListFragment;
    private Fragment userFragment;
    public MutableLiveData<Integer> currentItemLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        fragmentManager = getSupportFragmentManager();
        initTitleBar();
        initOnMenuSelectorListener();
        changeMenu(1);
        handleMessage();
        updateFmcTocken();
    }

    private void handleMessage() {
        final Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(bundle.containsKey("orderId")){
                    ARouter.getInstance().build("/order/detail").withString("order_code",bundle.getString("orderId")).navigation();
                }else{

                }
            }
        },200);

    }

    private void updateFmcTocken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("firebase", "Refreshed token: " + refreshedToken);
        UserInfoDataBean userInfoData = (UserInfoDataBean) SPUtils.getInstance().readObject(SPKeys.USER_INFO);
        if(userInfoData!=null) {
            Map<String, Object> map = new HashMap<>();
            map.put("tokenid", Common.getToken());
            map.put("phone", userInfoData.getAccount_number());
            map.put("appkey", refreshedToken);
            String json = new Gson().toJson(map);
            ApiRepository.getUpdateFMCToken(json).subscribe(new RObserver<BaseResponse>() {
                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    LogUtils.d(baseResponse.getMsg());
                }
            });
        }
    }

    private void initTitleBar() {

    }

    private void initOnMenuSelectorListener() {
        binding.setListener(new OnMenuSelectorListener(){
            @Override
            public void onHomeSelect(View v) {
                super.onHomeSelect(v);
                changeMenu(0);
            }

            @Override
            public void onOrderSelect(View v) {
                super.onOrderSelect(v);
                changeMenu(1);
            }

            @Override
            public void onMySelect(View v) {
                super.onMySelect(v);
                changeMenu(2);
            }
        });
    }
    public void changeMenu(int item){
        synchronized (fragmentManager) {
            FragmentTransaction trans = fragmentManager.beginTransaction();
            setMenuStyle(item);
            hideFrament(trans);
            setFragment(item, trans);
            trans.commitAllowingStateLoss();
        }
    }

    /**
     * 隐藏所有的fragment(编程初始化状态)
     *
     * @param trans
     */
    private void hideFrament(FragmentTransaction trans) {
        if (orderPushlishFragment != null) {
            trans.hide(orderPushlishFragment);
        }
        if (orderListFragment != null) {
            trans.hide(orderListFragment);
        }
        if (userFragment != null) {
            trans.hide(userFragment);
        }
    }

    /**
     * 设置menu样式
     *
     * @param vID
     */
    private void setMenuStyle(int vID) {
        //列表
        if (vID == 0) {
            binding.tvMenu2.setSelected(false);
            binding.imgMenu2.setSelected(false);
            binding.tvMenu3.setSelected(false);
            binding.imgMenu3.setSelected(false);
            binding.tvMenu1.setSelected(true);
            binding.imgMenu1.setSelected(true);
        }
        // 地图
        if (vID == 1) {
            binding.tvMenu1.setSelected(false);
            binding.imgMenu1.setSelected(false);
            binding.tvMenu3.setSelected(false);
            binding.imgMenu3.setSelected(false);
            binding.tvMenu2.setSelected(true);
            binding.imgMenu2.setSelected(true);
        }
        // 事件
        if (vID == 2) {
            binding.tvMenu1.setSelected(false);
            binding.imgMenu1.setSelected(false);
            binding.tvMenu2.setSelected(false);
            binding.imgMenu2.setSelected(false);
            binding.tvMenu3.setSelected(true);
            binding.imgMenu3.setSelected(true);
        }
    }

    /**
     * 设置Fragment
     *
     * @param vID
     * @param trans
     */
    private void setFragment(int vID, FragmentTransaction trans) {
        switch (vID) {
            case 0:
                if (orderPushlishFragment == null) {
                    orderPushlishFragment = new OrderPublishFragment();
                    trans.add(R.id.content, orderPushlishFragment);
                } else {
                    trans.show(orderPushlishFragment);
                }
                break;
            case 1:
                if (orderListFragment == null) {
                    orderListFragment = new OrderListFragment();
                    trans.add(R.id.content, orderListFragment);
                } else {
                    trans.show(orderListFragment);
                }
                break;
            case 2:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    trans.add(R.id.content, userFragment);
                } else {
                    trans.show(userFragment);
                }
                break;
            default:
                break;
        }
    }

}
