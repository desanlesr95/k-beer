package com.lesr.k_beer.model.retrofit;

import com.lesr.k_beer.model.Beer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/beers")
    Call<Beer[]> getBeer(@Query("page") int page);



}
