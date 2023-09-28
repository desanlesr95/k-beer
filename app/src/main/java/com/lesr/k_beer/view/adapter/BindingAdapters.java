package com.lesr.k_beer.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.lesr.k_beer.R;

public class BindingAdapters {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext()).load(url).placeholder(R.drawable.placeholder).into(view);
    }

    @BindingAdapter("setAbv")
    public static void setAlcoholPorcent(TextView view, Float abv){
       view.setText("% "+abv + " de alcohol");
    }
}
