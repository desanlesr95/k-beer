package com.lesr.k_beer.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lesr.k_beer.model.Amount;

@Dao
public interface AmountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAmount(Amount amounts);

    @Query("DELETE from amount")
    void deleteAll();

    @Query("SELECT * from amount where id_amount = :id ")
    Amount getAmountById(int id);

    @Query("DELETE from amount where id_amount IN (:ids)")
    void deleteByIds(int[] ids);
}
