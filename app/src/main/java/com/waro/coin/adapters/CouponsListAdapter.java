package com.waro.coin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waro.coin.databinding.CouponItemBinding;
import com.waro.coin.interfaces.CouponInterface;
import com.waro.coin.model.CouponsResponse;

import java.util.List;


public class CouponsListAdapter extends RecyclerView.Adapter<CouponsListAdapter.ViewHolder> {

    List<CouponsResponse.CouponsBean> modelList;
    Context context;
    CouponInterface callback;

    public CouponsListAdapter(List<CouponsResponse.CouponsBean> modelList, Context context, CouponInterface callback) {
        this.modelList = modelList;
        this.context = context;
        this.callback = callback;
    }


    @NonNull
    @Override
    public CouponsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CouponItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CouponsListAdapter.ViewHolder holder, int position) {
        CouponsResponse.CouponsBean couponsBean = modelList.get(position);
        holder.rowItemBinding.txtCouponDetails.setText(modelList.get(position).getType());
        holder.rowItemBinding.txtCouponName.setText(modelList.get(position).getCouponCode());

        holder.rowItemBinding.txtApply.setOnClickListener(v -> {
         callback.callbackMethod(couponsBean);

        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CouponItemBinding rowItemBinding;

        public ViewHolder(@NonNull CouponItemBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }
    }
}
