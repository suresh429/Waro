package com.waro.coin.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.waro.coin.R;
import com.waro.coin.adapters.CouponsListAdapter;
import com.waro.coin.helper.UserSessionManager;
import com.waro.coin.helper.Util;
import com.waro.coin.interfaces.CouponInterface;
import com.waro.coin.model.CouponsResponse;
import com.waro.coin.network.ApiInterface;
import com.waro.coin.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    private final CouponInterface callback;
    private ItemClickListener mListener;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imgClose;
    String customerId;
    UserSessionManager userSessionManager;

    public ActionBottomDialogFragment(CouponInterface callback) {
        this.callback=callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerCoupons);
        progressBar = view.findViewById(R.id.progressBar);
        imgClose = view.findViewById(R.id.imgClose);
        userSessionManager=new UserSessionManager(getContext());
        customerId=userSessionManager.getUserDetails().get("id");
        couponsData();

        imgClose.setOnClickListener(v -> dismiss());

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }*/
    }
    @Override
    public void onDetach() {
        super.onDetach();
       //mListener = null;
    }


    public interface ItemClickListener {
        void onItemClick(String item);
    }

    private void couponsData() {
        progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_id", customerId);

        Call<CouponsResponse> call = RetrofitService.createService(ApiInterface.class, requireContext()).getCouponDetailsList(jsonObject);
        call.enqueue(new Callback<CouponsResponse>() {
            @Override
            public void onResponse(@NonNull Call<CouponsResponse> call, @NonNull Response<CouponsResponse> response) {

                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    List<CouponsResponse.CouponsBean> couponsBeanList = response.body().getCoupons();
                    CouponsListAdapter adapter = new CouponsListAdapter(couponsBeanList,getContext(),callback);
                    recyclerView.setAdapter(adapter);


                } else if (response.errorBody() != null) {
                    progressBar.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFailure(@NonNull Call<CouponsResponse> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Util.snackBar(requireView().getRootView(), t.getMessage(), Color.RED);
            }
        });
    }
}
