package com.lesr.k_beer.view.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lesr.k_beer.databinding.CardHopBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.model.Hops;

public class HopAdapter extends RecyclerView.Adapter<HopAdapter.ViewHolder>{

    Hops hops[];

    public HopAdapter(Hops[] hops) {
        this.hops = hops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardHopBinding binding = CardHopBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(hops[position]);
    }

    @Override
    public int getItemCount() {
        return hops.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public CardHopBinding binding;
        public ViewHolder(@NonNull CardHopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Hops hop){
            binding.setHop(hop);
            binding.executePendingBindings();
        }

    }
}
