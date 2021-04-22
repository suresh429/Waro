package com.waro.coin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.waro.coin.R;
import com.waro.coin.databinding.CouponItemBinding;
import com.waro.coin.databinding.ShopItemBinding;
import com.waro.coin.model.CouponsResponse;
import com.waro.coin.model.ShopsListResponse;

import java.util.List;

import static com.waro.coin.network.RetrofitService.IMAGE_HOME_URL;


public class CouponsListAdapter extends RecyclerView.Adapter<CouponsListAdapter.ViewHolder> {

    List<CouponsResponse.CouponsBean> modelList;
    Context context;

    public CouponsListAdapter(List<CouponsResponse.CouponsBean> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CouponsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CouponItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CouponsListAdapter.ViewHolder holder, int position) {

        holder.rowItemBinding.txtCouponDetails.setText(modelList.get(position).getType());
        holder.rowItemBinding.txtCouponName.setText(modelList.get(position).getCouponCode());

        holder.rowItemBinding.txtApply.setOnClickListener(v -> {

           /* Bundle bundle = new Bundle();
            bundle.putInt("shopId", modelList.get(position).getId());
            bundle.putString("shopName", modelList.get(position).getShopname());
            bundle.putString("shopImage", modelList.get(position).getImage());
            bundle.putString("shopLocation", modelList.get(position).getAddress());
            bundle.putString("shopDescription", modelList.get(position).getDescription());
            bundle.putString("shopOpenTime", modelList.get(position).getOpentime());
            bundle.putString("shopCloseTime", modelList.get(position).getClosetime());
            bundle.putString("shopContact", modelList.get(position).getPhone());

            Navigation.findNavController(v).navigate(R.id.restaurantsItemsListFragment, bundle);*/
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
