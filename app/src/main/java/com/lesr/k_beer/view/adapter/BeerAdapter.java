package com.lesr.k_beer.view.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.CardBeerBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.util.Constants;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    public ArrayList<Beer> beers;
    public Activity activity;
    NavController navController;

    public BeerAdapter(Activity activity){
        this.beers = new ArrayList<>();
        this.navController = Navigation.findNavController(activity, R.id.nav_controller);
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
        holder.bind(beers.get(position));
    }

    public void addBeers(Beer[] beers){
        for (Beer beer : beers) {
            this.beers.add(beer);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public CardBeerBinding binding;
        public ViewHolder(@NonNull CardBeerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Beer beer){
            binding.setBeer(beer);
            binding.cardBeer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle= new Bundle();
                    bundle.putParcelable(Constants.BEER_DETAIL,beer);
                    navController.navigate(R.id.action_fragment_beerList_to_fragment_beerDetail,bundle);
                }
            });
            binding.executePendingBindings();
        }

    }

}
