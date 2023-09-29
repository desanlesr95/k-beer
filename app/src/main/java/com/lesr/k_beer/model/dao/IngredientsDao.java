package com.lesr.k_beer.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.model.Ingredients;

@Dao
public interface IngredientsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertIngredient(Ingredients ingredients);

    @Query("SELECT * from ingredients")
    Ingredients[] getIngredients();

    @Query("SELECT * from ingredients where idBeer = :idBeer")
    Ingredients getIngredients(int idBeer);
    @Query("SELECT id_ingredient from ingredients where idBeer = :idBeer")
    int[] getIngredientsIdsByBeer(int idBeer);
    @Query("DELETE from ingredients where idBeer = :idBeer")
    void deleteAllByIdBeer(int idBeer);
}
