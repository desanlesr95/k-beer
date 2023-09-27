package com.lesr.k_beer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesr.k_beer.databinding.CardBeerBinding;
import com.lesr.k_beer.model.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    public Beer[] beers;

    public BeerAdapter(Beer[] beers){
        this.beers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardBeerBinding binding = CardBeerBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(beers[position]);
    }

    @Override
    public int getItemCount() {
        return beers.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public CardBeerBinding binding;
        public ViewHolder(@NonNull CardBeerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Beer beer){
            binding.setBeer(beer);
            binding.executePendingBindings();
        }

    }

}
