package com.waro.coin.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.waro.coin.R;
import com.waro.coin.activities.HomeActivity;
import com.waro.coin.databinding.FragmentPlaceorderBinding;
import com.waro.coin.helper.UserSessionManager;
import com.waro.coin.helper.Util;
import com.waro.coin.interfaces.CouponInterface;
import com.waro.coin.model.CouponsResponse;
import com.waro.coin.model.PlaceOrderResponse;
import com.waro.coin.network.ApiInterface;
import com.waro.coin.network.RetrofitService;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.waro.coin.network.RetrofitService.IMAGE_HOME_URL;


public class PlaceOrderFragment extends Fragment implements CouponInterface {
    private static final String TAG = "PlaceOrderFragment";
    private CouponInterface callback;
    private FragmentPlaceorderBinding binding;
    String customerId, shop_id,date_time;
    double total_amt;
    int grandTotal, itemCount, address_id, deliveryCharge;
    ActionBottomDialogFragment addPhotoBottomDialogFragment;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlaceorderBinding.inflate(inflater, container, false);
        callback=this;
        UserSessionManager userSessionManager = new UserSessionManager(requireContext());
        HashMap<String, String> userDetails = userSessionManager.getUserDetails();
        customerId = userDetails.get("id");

        assert getArguments() != null;
        grandTotal = getArguments().getInt("grandTotal");
        itemCount = getArguments().getInt("itemCount");
        address_id = getArguments().getInt("address_id");
        shop_id = getArguments().getString("shop_id");
        deliveryCharge = getArguments().getInt("DeliveryCharge");

        date_time = new SimpleDateFormat("MMM dd, yyyy HH:mm a", Locale.getDefault()).format(new Date());

        Log.d(TAG, "onCreateView: "+date_time);
        binding.txtShopName.setText(userSessionManager.getShopDetails().get("shopName"));
        binding.txtAddreess.setText(userSessionManager.getShopDetails().get("shopLocation"));
        binding.txtTime.setText(userSessionManager.getShopDetails().get("shopOpenTime") + " - " + userSessionManager.getShopDetails().get("shopCloseTime"));
        binding.txtMobile.setText(userSessionManager.getShopDetails().get("shopContact"));
        binding.txtDescription.setText(userSessionManager.getShopDetails().get("shopDescription"));
        Glide.with(requireContext()).load(IMAGE_HOME_URL + userSessionManager.getShopDetails().get("shopImage")).error(R.drawable.placeholder).into(binding.imgShop);


        binding.actionLayout.txtActionBarTitle.setText("Place Order");
        binding.actionLayout.badgeCart.setVisibility(View.GONE);
        binding.actionLayout.txtActionBarTitle.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());

        orderCalculation(0);

        binding.btnPlaceOrder.setOnClickListener(v -> PlaceOrderData());

        binding.txtViewOffers.setOnClickListener(v -> showBottomSheet());


        return binding.getRoot();

    }

    private void orderCalculation(int discountPrice) {
        if (discountPrice==0){
            binding.txtCouponPrice.setVisibility(View.GONE);
            binding.txtCoupon.setVisibility(View.GONE);
        }else {
            binding.txtCouponPrice.setVisibility(View.VISIBLE);
            binding.txtCoupon.setVisibility(View.VISIBLE);
        }

        binding.txtTotalCount.setText("" + itemCount);
        binding.txtTotalItems.setText("\u20b9" + String.format("%.2f", (double) grandTotal));
        binding.txtDeliveryPrice.setText("\u20b9" + String.format("%.2f", (double) deliveryCharge));


        binding.txtCouponPrice.setText("-"+"\u20b9" + String.format("%.2f", (double) discountPrice));
        total_amt = (grandTotal + (double) deliveryCharge)-discountPrice;
        binding.txtGrandTotal.setText("\u20b9" + String.format("%.2f", total_amt));
    }

    private void PlaceOrderData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_id", customerId);
        jsonObject.addProperty("total_amt", total_amt);
        jsonObject.addProperty("delivery_charges", deliveryCharge);
        jsonObject.addProperty("customer_comments", Objects.requireNonNull(binding.etComments.getText()).toString());
        jsonObject.addProperty("address_id", address_id);
        jsonObject.addProperty("shop_id", shop_id);
        jsonObject.addProperty("date_time", date_time);
        Call<PlaceOrderResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getPlaceOrderList(jsonObject);
        call.enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlaceOrderResponse> call, @NonNull Response<PlaceOrderResponse> response) {

                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

                    assert response.body() != null;
                    displayAlert("Success", response.body().getMsg());

                } else if (response.errorBody() != null) {
                    binding.progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    displayAlert("Failure", response.body().getMsg());

                }
            }

            @Override
            public void onFailure(@NonNull Call<PlaceOrderResponse> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Util.snackBar(requireView().getRootView(), t.getMessage(), Color.RED);
            }
        });
    }


    private void displayAlert(@NonNull String alertTitle, @Nullable String message) {


        ViewGroup viewGroup = binding.getRoot().findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.success_order_diloge, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtMsg = dialogView.findViewById(R.id.txtMsg);
        ImageView image = dialogView.findViewById(R.id.image);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);


        txtTitle.setText(alertTitle);
        txtMsg.setText(message);
        image.setImageResource(R.drawable.ic_success);
        buttonOk.setOnClickListener(v -> {
            // Navigation.findNavController(v).navigate(R.id.navigation_home);
            Intent intentLogin = new Intent(requireContext(), HomeActivity.class);
            intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentLogin);
            alertDialog.dismiss();

        });


    }


    public void showBottomSheet() {
        addPhotoBottomDialogFragment = new ActionBottomDialogFragment(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(), ActionBottomDialogFragment.TAG);

    }

    @Override
    public void callbackMethod(CouponsResponse.CouponsBean couponsBean) {
        addPhotoBottomDialogFragment.dismiss();
        binding.txtViewOffers.setText("Remove");
        binding.txtPromoCode.setText(couponsBean.getCouponCode());
        binding.txtDiscount.setVisibility(View.VISIBLE);
        binding.txtDiscount.setText("- "+"\u20b9"+couponsBean.getValue());

        orderCalculation(Integer.parseInt(couponsBean.getValue()));

        binding.txtViewOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.txtViewOffers.getText().toString().equalsIgnoreCase("Remove")){
                     binding.txtPromoCode.setText(R.string.select_a_promo_code);
                     binding.txtViewOffers.setText(R.string.view_offers);
                     binding.txtDiscount.setVisibility(View.GONE);
                     binding.txtDiscount.setText("0.00");
                     orderCalculation(0);
                }else {
                    showBottomSheet();
                }


            }
        });
    }


}