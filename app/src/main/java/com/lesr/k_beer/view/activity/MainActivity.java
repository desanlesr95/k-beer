package com.lesr.k_beer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.ActivityMainBinding;
import com.lesr.k_beer.viewModel.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.init(this);
        viewModel.getUsername().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.edtName.setText(s);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addName(binding.edtName.getText().toString());
                startActivity(new Intent(getApplicationContext(),BeersActivity.class));
                finish();
            }
        });
    }
}