package com.lesr.k_beer.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lesr.k_beer.model.dao.AmountDao;
import com.lesr.k_beer.model.dao.BeerDao;
import com.lesr.k_beer.model.dao.HopsDao;
import com.lesr.k_beer.model.dao.IngredientsDao;
import com.lesr.k_beer.model.dao.MaltDao;
import com.lesr.k_beer.util.Constants;

@Database(entities = {Beer.class, Ingredients.class,Hops.class, Malt.class, Amount.class}, version = 3)
@TypeConverters(StringArrayConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract BeerDao beerDao();

    public abstract IngredientsDao ingredientsDao();

    public abstract HopsDao hopsDao();

    public abstract MaltDao maltDao();
    public  abstract AmountDao amountDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constants.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
