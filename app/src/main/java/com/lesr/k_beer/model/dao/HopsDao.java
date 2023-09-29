package com.lesr.k_beer.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.model.Malt;

@Dao
public interface HopsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHops(Hops hops);

    @Query("SELECT * from hops")
    Hops[] getHops();

    @Query("SELECT * from hops where ingredientsId = :ingredientsId")
    Hops[] getHopsArray(int ingredientsId);

    @Query("SELECT id_hop from hops where ingredientsId IN (:ingredients)")
    int[] getIdsByIngrendients(int[] ingredients);

    @Query("DELETE from hops where ingredientsId IN (:ingredients)")
    void deleteByIngredients(int[] ingredients);

    @Query("DELETE from hops")
    void deleteAll();
}
