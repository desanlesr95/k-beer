package com.lesr.k_beer.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lesr.k_beer.model.Amount;
import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.model.Malt;

@Dao
public interface MaltDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMalt(Malt malt);

    @Query("DELETE from malt")
    void deleteAll();

    @Query("SELECT * from amount where id_amount IN (:ids)")
    Amount[] getAmountArray(int ids);
    @Query("SELECT * from malt where ingredientsId = :ingredientsId")
    Malt[] getMaltArray(int ingredientsId);

    @Query("SELECT id_amount from malt where ingredientsId IN (:ingredients)")
    int[] getIdsByIngrendients(int[] ingredients);

    @Query("DELETE from malt where ingredientsId IN (:ingredients)")
    void deleteByIngredients(int[] ingredients);
}
