package com.waro.coin.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.waro.coin.R;
import com.waro.coin.adapters.ItemsListAdapter;
import com.waro.coin.databinding.FragmentItemsListBinding;
import com.waro.coin.helper.UserSessionManager;
import com.waro.coin.helper.Util;
import com.waro.coin.model.CartResponse;
import com.waro.coin.model.ItemDetailsResponse;
import com.waro.coin.model.ItemsListResponse;
import com.waro.coin.network.ApiInterface;
import com.waro.coin.network.RetrofitService;
import com.waro.coin.viewmodels.CartViewModel;
import com.waro.coin.viewmodels.ItemsListViewModel;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.waro.coin.network.RetrofitService.IMAGE_HOME_URL;


public class ItemsListFragment extends Fragment implements ItemsListAdapter.RestaurantItemInterface {

    ArrayList<ItemsListResponse.DataBean> dataBeanArrayList = new ArrayList<>();
    List<ItemDetailsResponse> itemDetailsResponseList = new ArrayList<>();

    ItemsListViewModel itemsListViewModel;
    CartViewModel cartViewModel;
    FragmentItemsListBinding binding;
    ItemsListAdapter itemsListAdapter;
    UserSessionManager userSessionManager;
    ItemsListAdapter.RestaurantItemInterface restaurantItemInterface;

    String shopName, customerId, shopImage, shopLocation, shopDescription, shopOpenTime, shopCloseTime, shopContact;
    int shopId,qty;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentItemsListBinding.inflate(inflater, container, false);

        userSessionManager = new UserSessionManager(requireContext());
        HashMap<String, String> userDetails = userSessionManager.getUserDetails();
        customerId = userDetails.get("id");

        itemsListViewModel = new ViewModelProvider(this).get(ItemsListViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        restaurantItemInterface = this;

        //hideBottomNav();

        Bundle bundle = getArguments();
        assert bundle != null;
        shopId = bundle.getInt("shopId");
        shopName = bundle.getString("shopName");
        shopImage = bundle.getString("shopImage");
        shopLocation = bundle.getString("shopLocation");
        shopDescription = bundle.getString("shopDescription");
        shopOpenTime = bundle.getString("shopOpenTime");
        shopCloseTime = bundle.getString("shopCloseTime");
        shopContact = bundle.getString("shopContact");


        binding.txtShopName.setText(shopName);
        binding.txtAddreess.setText(shopLocation);
        binding.txtTime.setText(shopOpenTime + " - " + shopCloseTime);
        binding.txtMobile.setText(shopContact);
        binding.txtDescription.setText(shopDescription);
        Glide.with(requireContext()).load(IMAGE_HOME_URL + shopImage).error(R.drawable.placeholder).into(binding.imgShop);

        binding.actionLayout.txtActionBarTitle.setText(shopName);
        binding.actionLayout.badgeCart.setVisibility(View.GONE);
        binding.actionLayout.txtActionBarTitle.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());

        itemsListData();
        return binding.getRoot();
    }

    private void itemsListData() {
       /* // init
        itemsListViewModel.init(shopId, customerId, requireActivity());

        // Alert toast msg
        itemsListViewModel.getToastObserver().observe(getViewLifecycleOwner(), message -> {
            // Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar.make(binding.getRoot().getRootView(), message, Snackbar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.BLACK);
            snackbar.show();

            Util.noNetworkAlert(getActivity(), message);


        });

        // progress bar
        itemsListViewModel.getProgressbarObservable().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                binding.progressBar.setVisibility(View.VISIBLE);

            } else {
                binding.progressBar.setVisibility(View.GONE);

            }
        });

        // get home data
        itemsListViewModel.getRepository().observe(getViewLifecycleOwner(), homeResponse -> {

            if (homeResponse.getStatus().equalsIgnoreCase("true")) {

                List<ItemsListResponse.DataBean> catDetailsBeanList = homeResponse.getData();

                for (ItemsListResponse.DataBean dataBean : catDetailsBeanList) {

                    if (dataBean.getCart() instanceof JsonObject) {
                        Type type = new TypeToken<ItemsListResponse.DataBean>() {
                        }.getType();
                        ItemsListResponse.DataBean item = new Gson().fromJson(dataBean.getCart(), type);
                        Log.d(TAG, "itemsListData: " + item.getCart());
                    } else {
                        Type type = new TypeToken<List<ItemsListResponse.DataBean>>() {
                        }.getType();
                        List<ItemsListResponse.DataBean> items = new Gson().fromJson(dataBean.getCart(), type);
                        Log.d(TAG, "itemsListData: " + items.get(0).getCart());
                    }
                }
                dataBeanArrayList.addAll(catDetailsBeanList);

                itemDetailsResponseList.clear();
                for (ItemsListResponse.DataBean product : catDetailsBeanList) {
                    itemDetailsResponseList.add(new ItemDetailsResponse(product.getId(), product.getItemname(), product.getSlug(), 0, product.getShopId(), product.getCategoryId(),
                            product.getSubcategoryId(), Double.parseDouble(product.getPrice()), product.getDescription(), product.getChoices(), product.getImage(), product.getStatus(), product.getCreatedAt(), product.getUpdatedAt()));

                }


                itemsListAdapter = new ItemsListAdapter(itemDetailsResponseList, getActivity(), this);
                binding.recyclerHomeList.setAdapter(itemsListAdapter);
                binding.progressBar.setVisibility(View.GONE);
                binding.errorLayout.txtError.setVisibility(View.GONE);
                itemsListAdapter.notifyDataSetChanged();

            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.errorLayout.txtError.setVisibility(View.VISIBLE);
            }


        });*/

        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("shop_id", shopId);
        jsonObject.addProperty("customer_id", customerId);

        Call<ItemsListResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getItemsList(jsonObject);
        call.enqueue(new Callback<ItemsListResponse>() {
            @Override
            public void onResponse(@NotNull Call<ItemsListResponse> call, @NotNull Response<ItemsListResponse> response) {
                if (response.isSuccessful()) {


                    binding.progressBar.setVisibility(View.GONE);
                    ItemsListResponse itemsListResponse = response.body();

                    assert itemsListResponse != null;
                    if (itemsListResponse.getStatus().equalsIgnoreCase("true")) {

                        List<ItemsListResponse.DataBean> catDetailsBeanList = itemsListResponse.getData();
                        dataBeanArrayList.addAll(catDetailsBeanList);

                        itemDetailsResponseList.clear();
                        for (ItemsListResponse.DataBean product : catDetailsBeanList) {

                            if (product.getCart() instanceof JsonObject) {
                                JsonElement item1 = product.getCart();

                                qty=item1.getAsJsonObject().get("qty").getAsInt();

                            } else if (product.getCart() instanceof JsonArray){
                                Type type = new TypeToken<List<ItemsListResponse.DataBean>>() {}.getType();
                                List<ItemsListResponse.DataBean> items = new Gson().fromJson(product.getCart(), type);

                                qty =items.size();
                            }

                            itemDetailsResponseList.add(new ItemDetailsResponse(product.getId(), product.getItemname(), product.getSlug(), qty, product.getShopId(), product.getCategoryId(),
                                    product.getSubcategoryId(), Double.parseDouble(product.getPrice()), product.getDescription(), product.getChoices(), product.getImage(), product.getStatus(), product.getCreatedAt(), product.getUpdatedAt()));

                        }


                        itemsListAdapter = new ItemsListAdapter(itemDetailsResponseList, requireActivity(), restaurantItemInterface);
                        binding.recyclerHomeList.setAdapter(itemsListAdapter);
                        binding.progressBar.setVisibility(View.GONE);
                        binding.errorLayout.txtError.setVisibility(View.GONE);
                        binding.noInternet.noInternet.setVisibility(View.GONE);
                        itemsListAdapter.notifyDataSetChanged();
                        calculateCartTotal();

                    }
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.noInternet.noInternet.setVisibility(View.GONE);
                    binding.errorLayout.txtError.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(@NotNull Call<ItemsListResponse> call, @NotNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.noInternet.noInternet.setVisibility(View.VISIBLE);
            }
        });


    }


    @Override
    public void onMinusClick(int position, ItemDetailsResponse itemDetailsResponse) {
        int i = itemDetailsResponseList.indexOf(itemDetailsResponse);

        if (itemDetailsResponse.getQty() > 0) {

            ItemDetailsResponse updatedItemDetailsResponse = new ItemDetailsResponse(itemDetailsResponse.getId(), itemDetailsResponse.getItemname(), itemDetailsResponse.getSlug(), (itemDetailsResponse.getQty() - 1), itemDetailsResponse.getShop_id(), itemDetailsResponse.getCategory_id(),
                    itemDetailsResponse.getSubcategory_id(), itemDetailsResponse.getPrice(), itemDetailsResponse.getDescription(), itemDetailsResponse.getChoices(), itemDetailsResponse.getImage(), itemDetailsResponse.getStatus(), itemDetailsResponse.getCreated_at(), itemDetailsResponse.getUpdated_at()
            );

            itemDetailsResponseList.remove(itemDetailsResponse);
            itemDetailsResponseList.add(i, updatedItemDetailsResponse);
            itemsListAdapter.notifyDataSetChanged();
            updateCart(updatedItemDetailsResponse);
            calculateCartTotal();
        }

    }

    @Override
    public void onPlusClick(int position, ItemDetailsResponse itemDetailsResponse) {
        int i = itemDetailsResponseList.indexOf(itemDetailsResponse);
        ItemDetailsResponse updatedItemDetailsResponse = new ItemDetailsResponse(itemDetailsResponse.getId(), itemDetailsResponse.getItemname(), itemDetailsResponse.getSlug(), (itemDetailsResponse.getQty() + 1), itemDetailsResponse.getShop_id(), itemDetailsResponse.getCategory_id(),
                itemDetailsResponse.getSubcategory_id(), itemDetailsResponse.getPrice(), itemDetailsResponse.getDescription(), itemDetailsResponse.getChoices(), itemDetailsResponse.getImage(), itemDetailsResponse.getStatus(), itemDetailsResponse.getCreated_at(), itemDetailsResponse.getUpdated_at()
        );

        itemDetailsResponseList.remove(itemDetailsResponse);
        itemDetailsResponseList.add(i, updatedItemDetailsResponse);
        itemsListAdapter.notifyDataSetChanged();
        updateCart(updatedItemDetailsResponse);
        calculateCartTotal();
    }

    @Override
    public void onAddClick(int position, ItemDetailsResponse itemDetailsResponse) {
        int i = itemDetailsResponseList.indexOf(itemDetailsResponse);
        ItemDetailsResponse updatedItemDetailsResponse = new ItemDetailsResponse(
                itemDetailsResponse.getId(), itemDetailsResponse.getItemname(), itemDetailsResponse.getSlug(), 1, itemDetailsResponse.getShop_id(), itemDetailsResponse.getCategory_id(),
                itemDetailsResponse.getSubcategory_id(), itemDetailsResponse.getPrice(), itemDetailsResponse.getDescription(), itemDetailsResponse.getChoices(), itemDetailsResponse.getImage(), itemDetailsResponse.getStatus(), itemDetailsResponse.getCreated_at(), itemDetailsResponse.getUpdated_at()

        );

        itemDetailsResponseList.remove(itemDetailsResponse);
        itemDetailsResponseList.add(i, updatedItemDetailsResponse);
        itemsListAdapter.notifyDataSetChanged();
        calculateCartTotal();

        addCart(customerId, itemDetailsResponse.getId());
    }

    // total Amount
    @SuppressLint("SetTextI18n")
    public void calculateCartTotal() {
        int grandTotal = 0;
        int itemCount = 0;
        for (ItemDetailsResponse order : itemDetailsResponseList) {

            grandTotal += order.getPrice() * order.getQty();

            if (order.getQty() > 0) {
                itemCount += order.getQty();
            }
        }
        if (grandTotal != 0) {
            binding.actionBottomCart.txtItemPrice.setText("\u20B9 " + grandTotal);
            binding.actionBottomCart.txtItemCount.setText(itemCount + " ITEMS");
            binding.actionBottomCart.getRoot().setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            bundle.putString("shop_id", String.valueOf(shopId));

            // save shop data
            userSessionManager.saveShopDetails(shopName, shopImage, shopLocation, shopDescription, shopOpenTime, shopCloseTime, shopContact);

            binding.actionBottomCart.txtViewCart.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.navigation_cart, bundle));
        } else {
            binding.actionBottomCart.getRoot().setVisibility(View.GONE);
        }


    }

    private void addCart(String customerId, int itemId) {


        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_id", customerId);
        jsonObject.addProperty("item_id", itemId);
        jsonObject.addProperty("qty", 1);
        jsonObject.addProperty("shop_id", shopId);
        Call<CartResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getCartInsertList(jsonObject);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call, @NonNull Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

                    CartResponse cartResponse = response.body();
                    assert cartResponse != null;
                    if (cartResponse.getStatus().equalsIgnoreCase("true")) {
                        Util.snackBar(requireView().getRootView(),"Item Added to Cart",Color.WHITE);
                    } else {
                        clearCartDialog(customerId, itemId);
                    }


                } else if (response.errorBody() != null) {
                    binding.progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call, @NonNull Throwable t) {
                Util.snackBar(requireView().getRootView(),t.getMessage(),Color.WHITE);
                binding.progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void clearCartDialog(String customerId, int itemId) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(Html.fromHtml("<b>" + "Replace cart item?" + "<b>"));
        builder.setMessage(Html.fromHtml("Do you want to clear the cart and Add items from " + "<b>" + shopName + "<b>" + " ?"));

        // add the buttons
        builder.setPositiveButton("YES", (dialog, which) -> emptyCartAndReInsert(customerId, itemId));
        builder.setNegativeButton("NO", (dialog, which) -> {
            dialog.dismiss();
            itemsListData();
            binding.actionBottomCart.getRoot().setVisibility(View.GONE);
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void emptyCartAndReInsert(String customerId, int itemId) {

        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_id", customerId);
        jsonObject.addProperty("item_id", itemId);
        jsonObject.addProperty("qty", 1);
        jsonObject.addProperty("shop_id", shopId);
        Call<CartResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).emptyAndInsertList(jsonObject);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call, @NonNull Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

                    CartResponse cartResponse = response.body();
                    if (Objects.requireNonNull(cartResponse).getStatus().equalsIgnoreCase("true")) {
                        Util.snackBar(requireView().getRootView(),"Item Added to Cart", Color.WHITE);


                    } else {

                        Util.snackBar(requireView().getRootView(),"Different Shop", Color.WHITE);
                    }


                } else if (response.errorBody() != null) {
                    binding.progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call, @NonNull Throwable t) {
                Util.snackBar(requireView().getRootView(),t.getMessage(),Color.WHITE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateCart(ItemDetailsResponse cartModel) {
        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_id", customerId);
        jsonObject.addProperty("item_id", cartModel.getId());
        jsonObject.addProperty("qty", cartModel.getQty());
        Call<CartResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getCartUpdateList(jsonObject);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call, @NonNull Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

                    Util.snackBar(requireView().getRootView(),"Item Updated",Color.WHITE);

                } else if (response.errorBody() != null) {
                    binding.progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call, @NonNull Throwable t) {
                Util.snackBar(requireView().getRootView(),t.getMessage(),Color.WHITE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }


}
