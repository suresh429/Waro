package com.waro.coin.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.waro.coin.model.ItemDetailsResponse;
import com.waro.coin.network.Repository;
import com.google.gson.JsonObject;

public class ItemDetailsViewModel extends ViewModel {
    private MutableLiveData<String> toastMessageObserver;
    private MutableLiveData<Boolean> progressbarObservable;
    private MutableLiveData<ItemDetailsResponse> mutableLiveData;


    public void initItemDetails(String item_id, Context context) {
        if (mutableLiveData != null) {
            return;
        }
        Repository repository = Repository.getInstance(context);


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item_id", item_id);

        mutableLiveData = repository.getItemDetailsData(jsonObject);
        progressbarObservable = repository.getProgressbarObservable();
        toastMessageObserver = repository.getToastObserver();


    }

    public LiveData<ItemDetailsResponse> getRepository() {
        return mutableLiveData;
    }

    public LiveData<String> getToastObserver() {
        return toastMessageObserver;
    }

    public MutableLiveData<Boolean> getProgressbarObservable() {
        return progressbarObservable;
    }


}