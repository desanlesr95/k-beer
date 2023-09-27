package com.lesr.k_beer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.ActivityBeersBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.view.adapter.BeerAdapter;
import com.lesr.k_beer.viewModel.BeerViewModel;

import java.util.Arrays;

public class BeersActivity extends AppCompatActivity {
    ActivityBeersBinding binding;
    BeerViewModel viewModel;
    ProgressDialog progressDialog;
    BeerAdapter beerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_beers);
        viewModel = new ViewModelProvider(this).get(BeerViewModel.class);
        progressDialog = new ProgressDialog(this);
        viewModel.init();
        viewModel.getLiveDataBeer().observe(this, new Observer<Beer[]>() {
            @Override
            public void onChanged(Beer[] beers) {
                progressDialog.dismiss();
                beerAdapter = new BeerAdapter(beers);
                binding.rvBeers.setAdapter(beerAdapter);
                Log.i("beers", Arrays.toString(beers));
            }
        });
        viewModel.getBeersApi();
        progressDialog.show();
    }
}