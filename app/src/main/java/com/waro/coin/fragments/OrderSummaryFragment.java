package com.waro.coin.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.waro.coin.R;
import com.waro.coin.adapters.OrderItemListAdapter;
import com.waro.coin.databinding.FragmentOrderSummaryBinding;
import com.waro.coin.model.OrderHistoryResponse;
import com.waro.coin.viewmodels.OrderHistoryViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.waro.coin.network.RetrofitService.IMAGE_HOME_URL;


public class OrderSummaryFragment extends Fragment {

    private static final String TAG = "OrderSummaryFragment";
    OrderHistoryViewModel orderHistoryViewModel;
    FragmentOrderSummaryBinding binding;
    OrderItemListAdapter adapter;
    int position;
    List<OrderHistoryResponse.OrdersBean> ordersBeanList;
    List<OrderHistoryResponse.OrdersBean.OrderItemBean> orderItemBeanList;
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderHistoryViewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        binding = FragmentOrderSummaryBinding.inflate(inflater, container, false);

        binding.actionLayout.txtActionBarTitle.setText("Order Summary");
        binding.actionLayout.badgeCart.setVisibility(View.GONE);
        binding.actionLayout.txtActionBarTitle.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());



        Bundle bundle = getArguments();
        if (bundle != null) {
            ordersBeanList =  bundle.getParcelableArrayList("shopListArray");
            orderItemBeanList =  bundle.getParcelableArrayList("orderListArray");
            position = bundle.getInt("position");
        }

       /* for (OrderHistoryResponse.OrdersBean ordersBean : ordersBeanList){
            orderItemBeanList = ordersBean.getOrderItem();
        }*/
        adapter = new OrderItemListAdapter(orderItemBeanList,position, getActivity());
        binding.recyclerHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.txtRestuarntName.setText(ordersBeanList.get(position).getShop().getShopname());
        binding.txtRestuarntLocation.setText(ordersBeanList.get(position).getShop().getAddress());
        Glide.with(requireActivity())
                .load(IMAGE_HOME_URL + ordersBeanList.get(position).getShop().getImage())
                .error(R.drawable.placeholder)
                .into(binding.imgShop);

        binding.txtOrderStatus.setText("This Order was "+ordersBeanList.get(position).getStatus());

        double itemTotal = Double.parseDouble(ordersBeanList.get(position).getTotalAmt()) - (Double.parseDouble(ordersBeanList.get(position).getDeliveryCharges()) - Double.parseDouble(ordersBeanList.get(position).getDiscountAmt()));
        binding.txtItemTotalPrice.setText("\u20b9"+String.format("%.2f",itemTotal));
        binding.txtDeliveryPrice.setText("\u20b9"+ ordersBeanList.get(position).getDeliveryCharges());

        if (ordersBeanList.get(position).getDiscountAmt() != null) {
            binding.txtDiscountPrice.setText("-"+"\u20b9" + ordersBeanList.get(position).getDiscountAmt());
        }else {
            binding.txtDiscountPrice.setText("\u20b9" + "0.00");
        }

        if ( !ordersBeanList.get(position).getCouponId().equalsIgnoreCase("0")) {
            binding.txtCouponCode.setText(ordersBeanList.get(position).getCoupon().getCouponCode());
        }else {
            binding.txtCouponCode.setText("No Coupon Applied");
        }
        binding.txtGrandTotalAmount.setText("\u20b9"+ ordersBeanList.get(position).getTotalAmt());
        binding.txtOrderNumber.setText(""+ordersBeanList.get(position).getId());
        binding.txtOrderMode.setText("Cash on Delivery");


        if(ordersBeanList.get(position).getAddress() != null ){
            binding.txtOrderPhone.setText(""+ordersBeanList.get(position).getAddress().getPhone());
            binding.txtOrderAddress.setText(ordersBeanList.get(position).getAddress().getAddr1()+","+
                    ordersBeanList.get(position).getAddress().getAddr2()+","+
                    ordersBeanList.get(position).getAddress().getLandmark()+","+
                    ordersBeanList.get(position).getAddress().getPincode());
        }else {
            binding.txtOrderPhone.setText("----------------");
            binding.txtOrderAddress.setText("---------------");
        }



        return binding.getRoot();
    }

}