package com.waro.co.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.waro.co.model.CustomerResponse;
import com.waro.co.network.Repository;
import com.google.gson.JsonObject;

public class CustomerInsertViewModel extends ViewModel {
    private MutableLiveData<String> toastMessageObserver;
    private MutableLiveData<Boolean> progressbarObservable;
    private MutableLiveData<CustomerResponse> mutableLiveData;

    public void initCustomerInsert(String name,String phonenumber,String password, Context context) {
        if (mutableLiveData != null) {
            return;
        }
        Repository repository = Repository.getInstance(context);


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("phonenumber", phonenumber);
        jsonObject.addProperty("password", password);
        mutableLiveData = repository.getCustomerInsertData(jsonObject);
        progressbarObservable = repository.getProgressbarObservable();
        toastMessageObserver = repository.getToastObserver();


    }


    public LiveData<CustomerResponse> getRepository() {
        return mutableLiveData;
    }

    public LiveData<String> getToastObserver() {
        return toastMessageObserver;
    }

    public MutableLiveData<Boolean> getProgressbarObservable() {
        return progressbarObservable;
    }


}