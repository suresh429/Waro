package com.waro.co.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.waro.co.R;
import com.waro.co.databinding.ActivityRegistrationBinding;
import com.waro.co.helper.Util;
import com.waro.co.viewmodels.CustomerViewModel;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegistrationBinding binding;
    String mobile;
    CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registration);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            mobile = getIntent().getStringExtra("mobile");
            binding.etMobile.setText(mobile);
            binding.etMobile.setEnabled(false);
        }

        binding.btnSignup.setOnClickListener(this);
        binding.closeImg.setOnClickListener(this);

        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

    }

    private void customerInsert(String name, String phone, String password) {

        customerViewModel.initCustomerInsert(name, phone, password, this);

        // progress bar
        customerViewModel.getProgressbarObservable().observe(this, aBoolean -> {
            if (aBoolean) {
                binding.progressLayout.progressBar1.setVisibility(View.VISIBLE);

            } else {
                binding.progressLayout.progressBar1.setVisibility(View.GONE);
            }
        });

        // Alert msg
        customerViewModel.getToastObserver().observe(this, message -> {
            if (message.equalsIgnoreCase("401")) {
                // Util.snackBar(binding.getRoot().getRootView(), message, Color.WHITE);
            } else if (message.equalsIgnoreCase("500")) {
                Util.snackBar(binding.getRoot().getRootView(), message, Color.WHITE);
            } else {
                Util.snackBar(binding.getRoot().getRootView(), message, Color.WHITE);
               }

        });

        // get  data
        customerViewModel.getRepository().observe(this, baseResponse -> {
            if (baseResponse.getStatus().equalsIgnoreCase("true")) {

                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } else {

                Intent intentLogin = new Intent(this, RegistrationActivity.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogin);
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signup) {

            String name = Objects.requireNonNull(binding.etName.getText()).toString();
            String password = Objects.requireNonNull(binding.etPassword.getText()).toString();
            if (name.isEmpty()) {
                Util.snackBar(binding.getRoot().getRootView(), "Name Can't be Empty", Color.WHITE);
            } else if (password.length() < 5) {
                Util.snackBar(binding.getRoot().getRootView(), "Password Should be 5 or more characters!", Color.WHITE);
            }else {
                customerInsert(name,mobile,password);
            }
        }else if (v.getId() == R.id.close_img){
            Intent intentLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
            intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentLogin);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent intentLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentLogin);
    }
}