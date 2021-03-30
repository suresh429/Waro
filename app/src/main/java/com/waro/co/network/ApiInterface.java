package com.waro.co.network;

import com.waro.co.model.AddressResponse;
import com.waro.co.model.BannerResponse;
import com.waro.co.model.CartResponse;
import com.waro.co.model.CategoriesResponse;
import com.waro.co.model.CitiesResponse;

import com.waro.co.model.CustomerResponse;
import com.waro.co.model.ItemDetailsResponse;
import com.waro.co.model.ItemsListResponse;
import com.waro.co.model.OrderHistoryResponse;
import com.waro.co.model.PlaceOrderResponse;
import com.waro.co.model.ShopsListResponse;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {


    @Headers("Content-Type: application/json")
    @POST("banners")
    Call<BannerResponse> getBannersList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("cities")
    Call<CitiesResponse> getCitiesList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("categories")
    Call<CategoriesResponse> getCategoriesList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("shops")
    Call<ShopsListResponse> getShopsList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("shop-items")
    Call<ItemsListResponse> getItemsList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("item-details")
    Call<ItemDetailsResponse> getItemsDetailsList(@Body JsonObject jsonObject);


    @Headers("Content-Type: application/json")
    @POST("customer")
    Call<CustomerResponse> getCustomerList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("customer/verify")
    Call<CustomerResponse> getCustomerVerifyList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("customer/insert")
    Call<CustomerResponse> getCustomerInsertList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("cart/insert-item")
    Call<CartResponse> getCartInsertList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("empty-cart-and-insert-new-item")
    Call<CartResponse> emptyAndInsertList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("cart/update-item")
    Call<CartResponse> getCartUpdateList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("cart/view-cart")
    Call<CartResponse> getCartViewList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("place-order")
    Call<PlaceOrderResponse> getPlaceOrderList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("addresses")
    Call<AddressResponse> getAddressList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("insert-address")
    Call<AddressResponse> getAddressInsertList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("update-address")
    Call<AddressResponse> getAddressUpdateList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("delete-address")
    Call<AddressResponse> getAddressDeleteList(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("order-history")
    Call<OrderHistoryResponse> getOrderHistoryList(@Body JsonObject jsonObject);
}
