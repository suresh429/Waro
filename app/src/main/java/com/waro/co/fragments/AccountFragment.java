package com.waro.co.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.waro.co.BuildConfig;
import com.waro.co.R;
import com.waro.co.activities.LoginActivity;
import com.waro.co.databinding.FragmentMyaccountBinding;
import com.waro.co.helper.UserSessionManager;
import com.waro.co.helper.Util;
import com.waro.co.viewmodels.CustomerViewModel;


public class AccountFragment extends Fragment implements View.OnClickListener {
    private UserSessionManager session;

    FragmentMyaccountBinding binding;
    private CustomerViewModel customerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        binding = FragmentMyaccountBinding.inflate(inflater, container, false);

        session = new UserSessionManager(requireContext());
        String name = session.getUserDetails().get("name");
        String mobile = session.getUserDetails().get("mobile");

        binding.txtName.setText(name);
        binding.txtPhone.setText(mobile);

        binding.txtLogout.setOnClickListener(this);
        binding.txtAddress.setOnClickListener(this);
        binding.txtOrders.setOnClickListener(this);
        binding.txtAbout.setOnClickListener(this);
        binding.txtHelp.setOnClickListener(this);
        binding.txtRateUs.setOnClickListener(this);
        binding.txtReferUs.setOnClickListener(this);
        binding.txtSendFeedBack.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txtLogout) {

            session.clearSession();
            Intent intentLogin = new Intent(requireActivity(), LoginActivity.class);
            intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentLogin);
        } else if (v.getId() == R.id.txtAddress) {
            Bundle bundle = new Bundle();
            bundle.putString("FROM", "Account");
            Navigation.findNavController(v).navigate(R.id.addressListFragment, bundle);
        } else if (v.getId() == R.id.txtOrders) {
            Navigation.findNavController(v).navigate(R.id.navigation_history);
        } else if (v.getId() == R.id.txtReferUs) {
            Util.shareMyApp(getActivity());
        } else if (v.getId() == R.id.txtRateUs) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));

            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details")));

            }
        }
    }
}