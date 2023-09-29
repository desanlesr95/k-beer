package com.lesr.k_beer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.ActivityMainBinding;
import com.lesr.k_beer.viewModel.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
    }


    public void hideActionBarTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setTitleActionBar(String title) {
        binding.toolbar.setTitle(title);
    }




}