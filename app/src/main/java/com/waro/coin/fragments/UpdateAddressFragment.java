package com.waro.coin.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.waro.coin.R;
import com.waro.coin.databinding.FragmentUpdateAddressBinding;
import com.waro.coin.helper.UserSessionManager;
import com.waro.coin.helper.Util;
import com.waro.coin.model.AddressResponse;
import com.waro.coin.network.ApiInterface;
import com.waro.coin.network.RetrofitService;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateAddressFragment extends Fragment implements View.OnClickListener {

    private FragmentUpdateAddressBinding binding;
    int addressId,grandTotal,deliveryCharge,itemCount;
    String customerId,from,name,phone,address1,address2,landmark,pincode,shop_id;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateAddressBinding.inflate(inflater, container, false);

        UserSessionManager userSessionManager = new UserSessionManager(requireContext());
        HashMap<String, String> userDetails = userSessionManager.getUserDetails();
        customerId = userDetails.get("id");

        assert getArguments() != null;
        addressId =getArguments().getInt("address_id");
        name =getArguments().getString("name");
        phone =getArguments().getString("phone");
        address1 =getArguments().getString("address1");
        address2 =getArguments().getString("address2");
        landmark =getArguments().getString("landmark");
        pincode =getArguments().getString("pincode");
        from = getArguments().getString("FROM");
        grandTotal=getArguments().getInt("grandTotal");
        itemCount=getArguments().getInt("itemCount");
        shop_id=getArguments().getString("shop_id");
        deliveryCharge = getArguments().getInt("DeliveryCharge");


        binding.actionLayout.txtActionBarTitle.setText("Update Address");
        binding.actionLayout.badgeCart.setVisibility(View.GONE);
        binding.actionLayout.txtActionBarTitle.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());


        // set data from intent
        binding.name.setText(name);
        binding.phone.setText(phone);
        binding.address1.setText(address1);
        binding.address2.setText(address2);
        binding.landMark.setText(landmark);
        binding.pincode.setText(pincode);

        binding.btnSubmit.setOnClickListener(this);


        return binding.getRoot();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            Util.keypadHide(requireActivity());

            String address1 = Objects.requireNonNull(binding.address1.getText()).toString();
            String address2 = Objects.requireNonNull(binding.address2.getText()).toString();
            String landmark = Objects.requireNonNull(binding.landMark.getText()).toString();
            String pincode = Objects.requireNonNull(binding.pincode.getText()).toString();
            String name = Objects.requireNonNull(binding.name.getText()).toString();
            String phone = Objects.requireNonNull(binding.phone.getText()).toString();

            if (!address1.isEmpty()  && !address2.isEmpty() && !landmark.isEmpty() && !pincode.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {
                UpdateAddressData(address1, address2, landmark, pincode,name,phone);
            } else {
                Util.snackBar(v, "Please Fill Required Fields!", Color.RED);
            }

        }
    }

    private void UpdateAddressData(String address1, String address2, String landmark, String pincode,String name,String phone) {
        binding.progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", addressId);
        jsonObject.addProperty("customer_id", customerId);
        jsonObject.addProperty("addr1", address1);
        jsonObject.addProperty("addr2", address2);
        jsonObject.addProperty("landmark", landmark);
        jsonObject.addProperty("pincode", pincode);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("phone", phone);
        Call<AddressResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getAddressUpdateList(jsonObject);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddressResponse> call, @NonNull Response<AddressResponse> response) {

                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

                    Toast.makeText(requireContext(), "Address Added", Toast.LENGTH_SHORT).show();
                    // Navigation.findNavController(binding.getRoot()).navigate(R.id.addressListFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("FROM",from);
                    bundle.putInt("grandTotal",grandTotal);
                    bundle.putInt("itemCount",itemCount);
                    bundle.putInt("DeliveryCharge",deliveryCharge);
                    NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.addressListFragment, true).build();
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.addressListFragment, bundle, navOptions);

                } else if (response.errorBody() != null) {
                    binding.progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(@NonNull Call<AddressResponse> call, @NonNull Throwable t) {
                Util.snackBar(requireView().getRootView(),t.getMessage(),Color.RED);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}