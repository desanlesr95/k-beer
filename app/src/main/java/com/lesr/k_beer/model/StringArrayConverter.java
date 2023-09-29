package com.lesr.k_beer.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class StringArrayConverter {
    @TypeConverter
    public static String fromStringArray(String[] stringArray) {
        return new Gson().toJson(stringArray);
    }

    @TypeConverter
    public static String[] toStringArray(String string) {
        Type type = new TypeToken<String[]>() {}.getType();
        return new Gson().fromJson(string, type);
    }
}

