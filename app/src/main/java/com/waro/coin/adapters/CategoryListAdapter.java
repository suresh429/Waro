package com.waro.coin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.waro.coin.R;
import com.waro.coin.databinding.CatgoeryListItemBinding;
import com.waro.coin.databinding.RestaurantItemBinding;
import com.waro.coin.model.CategoriesResponse;
import com.waro.coin.model.ShopsListResponse;
import com.waro.coin.network.ApiInterface;
import com.waro.coin.network.RetrofitService;
import com.waro.coin.viewmodels.ShopsViewModel;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Objects;

import kotlin.collections.CollectionsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.waro.coin.network.RetrofitService.IMAGE_HOME_URL;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    List<CategoriesResponse.DataBean> modelList;
    Context context;
    String cityId;
    CatSubListAdapter catSubListAdapter;
    ShopsViewModel shopsViewModel;

    public CategoryListAdapter(List<CategoriesResponse.DataBean> modelList, Context context, String cityId) {
        this.modelList = modelList;
        this.context = context;
        this.cityId = cityId;
        shopsViewModel = new ViewModelProvider((FragmentActivity)context).get(ShopsViewModel.class);
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CatgoeryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {

       /* UserSessionManager userSessionManager = new UserSessionManager(context);
        HashMap<String, String> location = userSessionManager.getLocationDetails();
        String cityId = location.get("cityId");*/
        CategoriesResponse.DataBean dataBean = modelList.get(position);

        holder.rowItemBinding.catName.setText(modelList.get(position).getCategoryname());

        Glide.with(context).load(IMAGE_HOME_URL + modelList.get(position).getImage())
                .error(R.drawable.placeholder).into(holder.rowItemBinding.catImage);


        shopList(cityId,dataBean.getId(),holder.rowItemBinding.shopListRecycler);

        holder.rowItemBinding.getRoot().setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putInt("city_id", Integer.parseInt(Objects.requireNonNull(cityId)));
            bundle.putInt("category_id", modelList.get(position).getId());
            bundle.putString("categoryname", modelList.get(position).getCategoryname());

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.shopsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CatgoeryListItemBinding rowItemBinding;

        public ViewHolder(@NonNull CatgoeryListItemBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }
    }

    private void shopList(String city_id, int category_id, RecyclerView shopListRecycler){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city_id", city_id);
        jsonObject.addProperty("category_id", 1);
        Call<ShopsListResponse> call = RetrofitService.createService(ApiInterface.class, context).getShopsList(jsonObject);
        call.enqueue(new Callback<ShopsListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ShopsListResponse> call, @NonNull Response<ShopsListResponse> response) {

                if (response.isSuccessful()) {
                    List<ShopsListResponse.DataBean> catDetailsBeanList = response.body().getData();
                    List<ShopsListResponse.DataBean> filterDataBean = CollectionsKt.filter(catDetailsBeanList, s -> !s.getStatus().equals("0"));

                   // CatSubListAdapter adapter = new CatSubListAdapter(filterDataBean,context);
                    ShopsListAdapter adapter = new ShopsListAdapter(filterDataBean,context);
                    shopListRecycler.setAdapter(adapter);


                } else if (response.errorBody() != null) {

                }
            }

            @Override
            public void onFailure(@NonNull Call<ShopsListResponse> call, @NonNull Throwable t) {
                /*binding.progressBar.setVisibility(View.GONE);
                binding.noInternet.noInternet.setVisibility(View.VISIBLE);*/
            }
        });
    }


}