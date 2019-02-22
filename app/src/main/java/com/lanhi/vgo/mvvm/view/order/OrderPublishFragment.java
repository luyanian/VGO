package com.lanhi.vgo.mvvm.view.order;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lanhi.vgo.R;
import com.lanhi.vgo.adapter.StateCityAdapter;
import com.lanhi.vgo.api.ApiRepository;
import com.lanhi.vgo.api.response.BaseResponse;
import com.lanhi.vgo.api.response.DistanceFeeResponse;
import com.lanhi.vgo.api.response.DistanceMatrixResponse;
import com.lanhi.vgo.api.response.GetStatesResponse;
import com.lanhi.vgo.common.Common;
import com.lanhi.vgo.common.OnEventListener;
import com.lanhi.vgo.common.RObserver;
import com.lanhi.vgo.databinding.OrderPublishFragmentBinding;
import com.lanhi.vgo.mvvm.model.OrderData;
import com.lanhi.vgo.mvvm.model.StateCityData;
import com.lanhi.vgo.mvvm.view.MainActivity;
import com.lanhi.vgo.mvvm.viewmodel.StateCityViewModel;
import com.lanhi.vgo.mvvm.viewmodel.OrderViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class OrderPublishFragment extends Fragment {
    OrderPublishFragmentBinding binding;
    OrderViewModel orderViewModel;
    StateCityViewModel stateCityViewModel;
    private List<StateCityData> stateDataLists = new ArrayList<>();
    private List<StateCityData> cityDataLists = new ArrayList<>();
    StateCityAdapter consignorStateAdapter;
    StateCityAdapter consignorCityAdapter;
    StateCityAdapter reciptStateAdapter;
    StateCityAdapter reciptCityAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_publish_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        consignorStateAdapter = new StateCityAdapter(getActivity());
        consignorCityAdapter = new StateCityAdapter(getActivity());
        reciptStateAdapter = new StateCityAdapter(getActivity());
        reciptCityAdapter = new StateCityAdapter(getActivity());
        stateCityViewModel = ViewModelProviders.of(this).get(StateCityViewModel.class);
        orderViewModel.getOrderPublishLiveData().observe(this, new Observer<OrderData>() {
            @Override
            public void onChanged(@Nullable OrderData orderData) {
                if(orderData!=null) {
                    initData(orderData);
                }
            }
        });
        initEventListener();
    }

    private void initData(final OrderData orderData) {
        binding.setData(orderData);
        binding.setStateCityViewModel(stateCityViewModel);

        stateCityViewModel.getStateLiveData().observe(this, new Observer<GetStatesResponse>() {
            @Override
            public void onChanged(@Nullable GetStatesResponse statesResponse) {
                stateDataLists.clear();
                stateDataLists.addAll(stateCityViewModel.getStateData());
                StateCityData stateData = new StateCityData("-1","州","-1","000000",StateCityData.STATE);
                stateDataLists.add(0, stateData); //insert a blank item on the top of the list

                consignorStateAdapter.changeData(stateDataLists);
                binding.setConsignorStateAdapter(consignorStateAdapter);

                reciptStateAdapter.changeData(stateDataLists);
                binding.setReciptStateAdapter(reciptStateAdapter);


                cityDataLists.clear();
                cityDataLists.addAll(stateCityViewModel.getCurrentCitiesData(stateData.getId()));
                StateCityData cityData = new StateCityData("-1","市","-1","000000",StateCityData.CITY);
                cityDataLists.add(0, cityData); //insert a blank item on the top of the list

                consignorCityAdapter.changeData(cityDataLists);
                binding.setConsignorCityAdapter(consignorCityAdapter);

                reciptCityAdapter.changeData(cityDataLists);
                binding.setReciptCityAdapter(reciptCityAdapter);

                stateCityViewModel.setSelectStateById("consignor",orderData.getConsignorStateId(),orderData.getConsignorCityId());
            }
        });

        stateCityViewModel.getCurrentConsignorSelectedStateData().observe(this, new Observer<StateCityData>() {
            @Override
            public void onChanged(@Nullable StateCityData stateCityData) {
                cityDataLists.clear();
                cityDataLists.addAll(stateCityViewModel.getCurrentCitiesData(stateCityData.getId()));
                StateCityData cityData = new StateCityData("-1","市","-1","000000",StateCityData.CITY);
                cityDataLists.add(0, cityData); //insert a blank item on the top of the list
                consignorCityAdapter.changeData(cityDataLists);
                stateCityViewModel.setSelectCityById("consignor",stateCityData.getId(),stateCityData.getSelecteCityId());
                if(!"-1".equals(stateCityData.getId())) {
                    orderData.setConsignorStateId(stateCityData.getId());
                    orderData.setConsignorStateName(stateCityData.getName());
                    countTimeAndFee();
                }
            }
        });

        stateCityViewModel.getCurrentReciptSelectedStateData().observe(this, new Observer<StateCityData>() {
            @Override
            public void onChanged(@Nullable StateCityData stateCityData) {
                cityDataLists.clear();
                cityDataLists.addAll(stateCityViewModel.getCurrentCitiesData(stateCityData.getId()));
                StateCityData cityData = new StateCityData("-1","市","-1","000000",StateCityData.CITY);
                cityDataLists.add(0, cityData); //insert a blank item on the top of the list
                reciptCityAdapter.changeData(cityDataLists);
                stateCityViewModel.setSelectCityById("recipt",stateCityData.getId(),stateCityData.getSelecteCityId());
                if(!"-1".equals(stateCityData.getId())) {
                    orderData.setRecipientStateId(stateCityData.getId());
                    orderData.setRecipientStateName(stateCityData.getName());
                    countTimeAndFee();
                }
            }
        });

        stateCityViewModel.getCurrentConsignorSelectedCityData().observe(this, new Observer<StateCityData>() {
            @Override
            public void onChanged(@Nullable StateCityData stateCityData) {
                if(!"-1".equals(stateCityData.getId())) {
                    orderData.setConsignorCityId(stateCityData.getId());
                    orderData.setConsignorCityName(stateCityData.getName());
                    countTimeAndFee();
                }
            }
        });
        stateCityViewModel.getCurrentReciptSelectedCityData().observe(this, new Observer<StateCityData>() {
            @Override
            public void onChanged(@Nullable StateCityData stateCityData) {
                if(!"-1".equals(stateCityData.getId())) {
                    orderData.setRecipientCityId(stateCityData.getId());
                    orderData.setRecipientCityName(stateCityData.getName());
                    countTimeAndFee();
                }
            }
        });
        stateCityViewModel.getConsignorAddressData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                countTimeAndFee();
            }
        });
        stateCityViewModel.getReciptAddressData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                countTimeAndFee();
            }
        });
    }

    private void initEventListener() {
        binding.setListener(new OnEventListener(){
            @Override
            public void orderPublish(View v) {
                super.orderPublish(v);
                orderViewModel.orderPublish(new RObserver<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        binding.setFocusable(false);
                        orderViewModel.clearInputInfo();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        if(mainActivity!=null){
                            mainActivity.changeMenu(1);
                        }
                    }
                });
            }

            @Override
            public void cancelOrderPublish(View v) {
                super.cancelOrderPublish(v);
                orderViewModel.clearInputInfo();
            }
        });
    }

    private void countTimeAndFee(){
        final OrderData orderData = orderViewModel.getOrderPublishLiveData().getValue();
        if(orderData==null||TextUtils.isEmpty(orderData.getConsignorStateName())||TextUtils.isEmpty(orderData.getConsignorCityName())
                ||TextUtils.isEmpty(orderData.getConsignorAddress())||TextUtils.isEmpty(orderData.getRecipientStateName())
                ||TextUtils.isEmpty(orderData.getRecipientCityName())||TextUtils.isEmpty(orderData.getRecipientAddress())){
            return;
        }
        String from = orderData.getConsignorStateName()+orderData.getConsignorCityName()+orderData.getConsignorAddress();
        String to = orderData.getRecipientStateName()+orderData.getRecipientCityName()+orderData.getRecipientAddress();
        Common.getDistance(from, to, new Consumer<DistanceMatrixResponse>() {
            @Override
            public void accept(DistanceMatrixResponse distanceMatrixResponse) throws Exception {
                if(distanceMatrixResponse.getRows()!=null&&distanceMatrixResponse.getRows().size()>0
                        &&distanceMatrixResponse.getRows().get(0)!=null
                        &&distanceMatrixResponse.getRows().get(0).getElements()!=null
                        &&distanceMatrixResponse.getRows().get(0).getElements().size()>0
                        &&distanceMatrixResponse.getRows().get(0).getElements().get(0)!=null
                        &&distanceMatrixResponse.getRows().get(0).getElements().get(0).getDuration()!=null){
                    final DistanceMatrixResponse.RowsBean.ElementsBean.DurationBean durationBean = distanceMatrixResponse.getRows()
                            .get(0).getElements().get(0).getDuration();
                    final DistanceMatrixResponse.RowsBean.ElementsBean.DistanceBean distanceBean = distanceMatrixResponse.getRows()
                            .get(0).getElements().get(0).getDistance();
                    if(distanceBean.getValue()>0){
                        Map map = new HashMap();
                        map.put("tokenid", Common.getToken());
                        ApiRepository.getDistanceFee(new Gson().toJson(map)).subscribe(new RObserver<DistanceFeeResponse>() {
                            @Override
                            public void onSuccess(DistanceFeeResponse distanceFeeResponse) {
                                if(distanceFeeResponse.getData()!=null&&distanceFeeResponse.getData().size()>0&&distanceFeeResponse.getData().get(0)!=null){
                                    double base_fee = distanceFeeResponse.getData().get(0).getBase_fee();
                                    double add_fee = distanceFeeResponse.getData().get(0).getAdditional_fee();
                                    double distance = distanceBean.getValue()/1000.00;
                                    SpannableStringBuilder builder = new SpannableStringBuilder();
                                    ForegroundColorSpan fixTextSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
                                    String fixText ="";
                                    if(distance>5) {
                                        double driver_fee = base_fee + ((distance-5) * add_fee);
                                        fixText = new DecimalFormat("$0.00").format(driver_fee);
                                    }else{
                                        fixText = new DecimalFormat("$0.00").format(base_fee);
                                    }
                                    builder.append(fixText);
                                    builder.setSpan(fixTextSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                                    orderData.setPostageFee(builder.toString());
                                    binding.editPostageFee.setText(builder);
                                }
                            }
                        });
                    }
                    if(durationBean.getValue()>0){
                        orderData.setTimeValue(distanceBean.getText());
                    }
                }
            }
        });
    }

}
