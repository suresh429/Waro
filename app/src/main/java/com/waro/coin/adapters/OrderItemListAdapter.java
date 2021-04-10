package com.waro.coin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waro.coin.databinding.HistoryListItemBinding;
import com.waro.coin.databinding.OrderSummaryListItemBinding;
import com.waro.coin.model.OrderHistoryResponse;

import java.util.List;


public class OrderItemListAdapter extends RecyclerView.Adapter<OrderItemListAdapter.ViewHolder> {

    List<OrderHistoryResponse.OrdersBean.OrderItemBean> modelList;
    Context context;
    int FromPosition;
    public OrderItemListAdapter(List<OrderHistoryResponse.OrdersBean.OrderItemBean> modelList, int FromPosition, Context context) {
        this.modelList = modelList;
        this.FromPosition = FromPosition;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(OrderSummaryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull OrderItemListAdapter.ViewHolder holder, int position) {

        OrderHistoryResponse.OrdersBean.OrderItemBean ordersBean = modelList.get(position);

        holder.rowItemBinding.txtItem.setText(ordersBean.getItem().getItemname());
        holder.rowItemBinding.txtItemQty.setText(ordersBean.getQty());
        holder.rowItemBinding.txtItemPrice.setText(ordersBean.getItem().getPrice());
        double totalPrice = Double.parseDouble(ordersBean.getPrice())*Double.parseDouble(ordersBean.getQty());
        holder.rowItemBinding.txtItemTotalPrice.setText("\u20b9"+String.format("%.2f", totalPrice));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        OrderSummaryListItemBinding rowItemBinding;

        public ViewHolder(@NonNull OrderSummaryListItemBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }
    }
}
