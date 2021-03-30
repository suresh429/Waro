package com.waro.co.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waro.co.databinding.CatgoeryListItemBinding;
import com.waro.co.databinding.LocationCardBinding;
import com.waro.co.model.CitiesResponse;

import java.util.List;


public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {

    List<CitiesResponse.DataBean> modelList;
    Context mContext;
    AdapterListner adapterListner;

    public LocationListAdapter(List<CitiesResponse.DataBean> modelList, Context mContext, AdapterListner adapterListner) {
        this.modelList = modelList;
        this.mContext = mContext;
        this.adapterListner = adapterListner;
    }

    @NonNull
    @Override
    public LocationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LocationCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationListAdapter.ViewHolder holder, int position) {

        holder.rowItemBinding.txtLocation.setText(modelList.get(position).getCity());
        holder.rowItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListner.onClick(modelList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        LocationCardBinding rowItemBinding;

        public ViewHolder(@NonNull LocationCardBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }
    }

    public interface AdapterListner {
        void onClick(CitiesResponse.DataBean product);
    }
}
