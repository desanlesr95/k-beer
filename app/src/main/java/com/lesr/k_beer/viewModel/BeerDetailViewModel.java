package com.lesr.k_beer.viewModel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import com.lesr.k_beer.model.AppDatabase;
import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.model.Malt;

import java.util.Arrays;

public class BeerDetailViewModel extends ViewModel {


    public AppDatabase db;
    Context context;

    public void init(Context context){
        this.context = context;
        db = AppDatabase.getInstance(context);
    }

    public Ingredients getIngredients(int beerID){
        Ingredients ingredients = db.ingredientsDao().getIngredients(beerID);
        System.out.println("view.model"+ingredients);
        if (ingredients == null){
            return new Ingredients();
        }
        System.out.println(ingredients);
        System.out.println(beerID);
        System.out.println(ingredients);
        Malt[] malts = db.maltDao().getMaltArray(ingredients.id_ingredient);
        Hops[] hops = db.hopsDao().getHopsArray(ingredients.id_ingredient);
        for (Malt malt:malts){
            malt.amount = db.amountDao().getAmountById(malt.id_amount);
        }
        for (Hops hop:hops){
            hop.amount = db.amountDao().getAmountById(hop.id_amount);
        }
        ingredients.malt = Arrays.asList(malts);
        ingredients.hops = Arrays.asList(hops);
        return ingredients;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }


}
