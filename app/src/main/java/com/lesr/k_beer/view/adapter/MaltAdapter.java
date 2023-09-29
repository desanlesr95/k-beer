package com.lesr.k_beer.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lesr.k_beer.databinding.CardMaltBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.model.Malt;

public class MaltAdapter extends  RecyclerView.Adapter<MaltAdapter.ViewHolder>{

    Malt[] malts;

    public MaltAdapter(Malt[] malts) {
        this.malts = malts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardMaltBinding binding = CardMaltBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(malts[position]);
    }

    @Override
    public int getItemCount() {
        return malts.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public CardMaltBinding binding;
        public ViewHolder(@NonNull CardMaltBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Malt malt){
            binding.setMalt(malt);
            binding.executePendingBindings();
        }

    }
}
