package com.lesr.k_beer.model.retrofit;

import com.lesr.k_beer.model.Beer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v2/beers?page=1")
    Call<Beer[]> getBeer();



}
