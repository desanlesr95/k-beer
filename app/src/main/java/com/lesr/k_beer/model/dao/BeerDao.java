package com.lesr.k_beer.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lesr.k_beer.model.Beer;

@Dao
public interface BeerDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBeer(Beer beer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeers(Beer[] beer);

    @Query("SELECT * from beer")
    Beer[] getBeers();

    @Query("DELETE from beer")
    void deleteAll();

}
