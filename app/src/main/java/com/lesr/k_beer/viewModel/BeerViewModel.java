package com.lesr.k_beer.viewModel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.lesr.k_beer.model.AppDatabase;
import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.model.Malt;
import com.lesr.k_beer.model.retrofit.ApiAdapter;
import com.lesr.k_beer.model.retrofit.ApiService;
import com.lesr.k_beer.model.Beer;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerViewModel extends ViewModel {
    ApiService apiService;
    public MutableLiveData<Beer[]> _beers = new MutableLiveData();
    LiveData beers = _beers;
    AppDatabase db;
    Activity activity;

    public void init(Activity activity){
        this.activity = activity;
        apiService = ApiAdapter.getApiService();
        db = AppDatabase.getInstance(activity.getApplicationContext());
    }


    public LiveData<Beer[]> getLiveDataBeer(){
        return beers;
    }

    public void getBeersApi(int page){
        apiService.getBeer(page).enqueue(new Callback<Beer[]>() {
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


    public void insertBeer(Beer[] beers){
        Observable<Boolean> source = Observable.create(emitter -> {
            try{
                for (Beer beer:beers){
                    int[] ingredientsIds = db.ingredientsDao().getIngredientsIdsByBeer(beer.id);
                    db.ingredientsDao().deleteAllByIdBeer(beer.id);

                    int[] maltIdsAmount = db.maltDao().getIdsByIngrendients(ingredientsIds);
                    db.maltDao().deleteByIngredients(ingredientsIds);

                    int[] hopsIdsAmount = db.hopsDao().getIdsByIngrendients(ingredientsIds);
                    db.hopsDao().deleteByIngredients(ingredientsIds);


                    db.amountDao().deleteByIds(maltIdsAmount);
                    db.amountDao().deleteByIds(hopsIdsAmount);


                    int beerId = (int) db.beerDao().insertBeer(beer);
                    beer.ingredients.idBeer = beerId;
                    int id_ingredient =(int) db.ingredientsDao().insertIngredient(beer.getIngredients());
                    for (Malt malt:beer.getIngredients().malt){
                        int id_amount = (int) db.amountDao().insertAmount(malt.amount);
                        malt.id_amount = id_amount;
                        malt.ingredientsId = id_ingredient;
                        db.maltDao().insertMalt(malt);
                    }

                    for (Hops hops:beer.getIngredients().hops){
                        int id_amount = (int) db.amountDao().insertAmount(hops.amount);
                        hops.id_amount = id_amount;
                        hops.ingredientsID = id_ingredient;
                        db.hopsDao().insertHops(hops);
                    }

                }
                emitter.onNext(true);
                emitter.onComplete();
            }catch (Exception e){
                emitter.onNext(false);
                emitter.onError(e);
            }
        });
        source.observeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(
                result ->{
                    System.out.println("1");
                    System.out.println(result);
                },error ->{
                    System.out.println("2");
                    System.out.println(error.getMessage());
                }
        );

    }



    public void getBeersBD(){



        Observable<Beer[]> source = Observable.create(new ObservableOnSubscribe<Beer[]>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Beer[]> emitter) throws Throwable {
                Beer[] beers = db.beerDao().getBeers();
                emitter.onNext(beers);
                emitter.onComplete();
            }
        });


        source.observeOn(Schedulers.io()).subscribe(
                result ->{
                    System.out.println(result);
                    activity.runOnUiThread(()->{
                        _beers.setValue(result);
                    });
                },error ->{
                    System.out.println("3");
                    System.out.println(error.getMessage());
                }
        );

    }


}
