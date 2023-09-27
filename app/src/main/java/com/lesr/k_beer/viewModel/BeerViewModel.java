package com.lesr.k_beer.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lesr.k_beer.model.retrofit.ApiAdapter;
import com.lesr.k_beer.model.retrofit.ApiService;
import com.lesr.k_beer.model.Beer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerViewModel extends ViewModel {
    ApiService apiService;
    MutableLiveData<Beer[]> _beers = new MutableLiveData();
    LiveData beers = _beers;

    public void init(){
        apiService = ApiAdapter.getApiService();
    }


    public LiveData<Beer[]> getLiveDataBeer(){
        return beers;
    }

    public void getBeersApi(){
        apiService.getBeer().enqueue(new Callback<Beer[]>() {
            @Override
            public void onResponse(Call<Beer[]> call, Response<Beer[]> response) {
                _beers.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Beer[]> call, Throwable t) {
                Log.i("error-conexion",t.getMessage());
                _beers.setValue(null);
            }
        });
    }
}
