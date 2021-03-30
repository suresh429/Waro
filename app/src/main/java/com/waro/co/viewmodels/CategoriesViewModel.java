package com.waro.co.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.waro.co.model.CategoriesResponse;
import com.waro.co.network.Repository;
import com.google.gson.JsonObject;

public class CategoriesViewModel extends ViewModel {

    private MutableLiveData<String> toastMessageObserver;
    private MutableLiveData<Boolean> progressbarObservable;
    private MutableLiveData<CategoriesResponse> mutableLiveData;


    public void initCategories( Context context) {
        if (mutableLiveData != null) {
            return;
        }
        Repository repository = Repository.getInstance(context);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("page","1");
        mutableLiveData = repository.getCategoriesData(jsonObject);
        progressbarObservable = repository.getProgressbarObservable();
        toastMessageObserver = repository.getToastObserver();


    }

    public LiveData<CategoriesResponse> getRepository() {
        return mutableLiveData;
    }

    public LiveData<String> getToastObserver() {
        return toastMessageObserver;
    }

    public MutableLiveData<Boolean> getProgressbarObservable() {
        return progressbarObservable;
    }
}