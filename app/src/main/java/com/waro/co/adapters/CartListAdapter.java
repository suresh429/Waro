package com.waro.co.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waro.co.R;
import com.waro.co.databinding.RestaurantsItemsListBinding;
import com.waro.co.model.CartModel;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.waro.co.network.RetrofitService.IMAGE_HOME_URL;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    List<CartModel> modelList;
    Context context;
     public RestaurantItemInterface restaurantItemInterface;
    public CartListAdapter(List<CartModel> modelList, Context context, RestaurantItemInterface restaurantItemInterface) {
        this.modelList = modelList;
        this.context = context;
        this.restaurantItemInterface = restaurantItemInterface;
    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RestaurantsItemsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {

        holder.rowItemBinding.txtItemName.setText(modelList.get(position).getItemName());
        holder.rowItemBinding.txtItemPrice.setText("\u20B9"+String.format("%.2f", modelList.get(position).getPrice()));
        holder.rowItemBinding.txtItemCategory.setText(modelList.get(position).getItemName());

        if (modelList.get(position).getChoice().equalsIgnoreCase("veg")){
            holder.rowItemBinding.imgItemType.setImageResource(R.drawable.ic_baseline_green_24);
        }else {
            holder.rowItemBinding.imgItemType.setImageResource(R.drawable.ic_baseline_red_24);
        }

        Glide.with(context).load(IMAGE_HOME_URL+modelList.get(position).getImage()).into(holder.rowItemBinding.imgItem);

        if (modelList.get(position).getCart_qty() == 0) {
            holder.rowItemBinding.productQuantity.setText("ADD");
            holder.rowItemBinding.productMinus.setVisibility(View.GONE);
            holder.rowItemBinding.productPlus.setVisibility(View.GONE);

        } else {
            holder.rowItemBinding.productQuantity.setText("" + modelList.get(position).getCart_qty());
            holder.rowItemBinding.productMinus.setVisibility(View.VISIBLE);
            holder.rowItemBinding.productPlus.setVisibility(View.VISIBLE);
        }

        holder.rowItemBinding.productQuantity.setOnClickListener(view -> {
            if (holder.rowItemBinding.productQuantity.getText().toString().equalsIgnoreCase("ADD")) {
                holder.rowItemBinding.productQuantity.setText("" + modelList.get(position).getCart_qty());
                holder.rowItemBinding.productMinus.setVisibility(View.VISIBLE);
                holder.rowItemBinding.productPlus.setVisibility(View.VISIBLE);

               // restaurantItemInterface.onAddClick(position, modelList.get(position));
            }
        });

        holder.rowItemBinding.productMinus.setOnClickListener(view -> {

            restaurantItemInterface.onMinusClick(position, modelList.get(position));

        });

        holder.rowItemBinding.productPlus.setOnClickListener(view -> {

            restaurantItemInterface.onPlusClick(position, modelList.get(position));

        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RestaurantsItemsListBinding rowItemBinding;

        public ViewHolder(@NonNull RestaurantsItemsListBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }
    }

    public interface RestaurantItemInterface {
        void onMinusClick(int position, CartModel itemDetailsResponse);

        void onPlusClick(int position, CartModel itemDetailsResponse);

    }


}
