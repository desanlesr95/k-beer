package com.lesr.k_beer.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lesr.k_beer.R;
import com.lesr.k_beer.model.Amount;
import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Malt;

public class BindingAdapters {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext()).load(url).placeholder(R.drawable.placeholder).into(view);
    }

    @BindingAdapter("setAbv")
    public static void setAlcoholPorcent(TextView view, Float abv){
        try{
            view.setText(abv + " % de alcohol");
        }catch (Exception e){
            view.setText("?");
        }

    }


    @BindingAdapter("floatToString")
    public static void floatToString(TextView view, Float number){
        try{
            view.setText(number.toString());
        }catch (Exception e){
            view.setText("?");
        }

    }

    @BindingAdapter("arrayToString")
    public static void arrayToString(TextView view,String[] array){
        String content = "";
        for (String item:array){
            content += item + "\n";
        }
        view.setText(content);
    }

    @BindingAdapter("amountString")
    public static void amountString(TextView textView, Amount amount){
        textView.setText( textView.getContext().getString(R.string.cant)+ " "+ amount.value + " " + amount.unit);
    }


    @BindingAdapter("getAdapterHop")
    public static void getAdapterHop(RecyclerView recyclerView, Hops[] hops){
        HopAdapter hopAdapter = new HopAdapter(hops);
        recyclerView.setAdapter(hopAdapter);
    }

    @BindingAdapter("getAdapterMalt")
    public static void getAdapterMalt(RecyclerView recyclerView, Malt[] malt){
        MaltAdapter maltAdapter = new MaltAdapter(malt);
        recyclerView.setAdapter(maltAdapter);
    }


    @BindingAdapter("setTextNameIngredient")
    public static void setTextNameIngredient(TextView textView,String name ){
        textView.setText( textView.getContext().getString(R.string.name)+ " "+ name);
    }
}
