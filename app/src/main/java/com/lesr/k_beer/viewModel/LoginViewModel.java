package com.lesr.k_beer.viewModel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lesr.k_beer.R;

public class LoginViewModel extends ViewModel {
    MutableLiveData<String> _username = new MutableLiveData<>();
    LiveData<String> username = _username;
    SharedPreferences sharedPreferences;
    String name_preference;


    public LiveData<String> getUsername() {
        return username;
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference), Context.MODE_PRIVATE);
        name_preference = context.getString(R.string.preference_name);
        _username.setValue(sharedPreferences.getString(name_preference,""));
    }

    public void addName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name_preference, name);
        editor.apply();
    }



}
