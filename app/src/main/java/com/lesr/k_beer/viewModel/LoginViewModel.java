package com.lesr.k_beer.viewModel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lesr.k_beer.R;
import com.lesr.k_beer.util.Constants;

public class LoginViewModel extends ViewModel {
    MutableLiveData<String> _username = new MutableLiveData<>();
    LiveData<String> username = _username;
    SharedPreferences sharedPreferences;
    String name_preference;


    public LiveData<String> getUsername() {
        return username;
    }
    Context context;
    public void init(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void requestNamePreference() {
        _username.setValue(getNamepreference());
    }

    public String getNamepreference() {
        return  sharedPreferences.getString(name_preference,"");

    }

    public void addName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name_preference, name);
        editor.apply();
    }



}
