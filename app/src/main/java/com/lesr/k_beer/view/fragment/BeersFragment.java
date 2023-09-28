package com.lesr.k_beer.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.FragmentBeersBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.view.MainActivity;
import com.lesr.k_beer.view.adapter.BeerAdapter;
import com.lesr.k_beer.viewModel.BeerViewModel;

import java.util.Arrays;


public class BeersFragment extends Fragment {
    FragmentBeersBinding binding;
    BeerViewModel viewModel;
    ProgressDialog progressDialog;
    BeerAdapter beerAdapter;
    int page = 1;

    public BeersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_beers,container,false);
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_controller);
        viewModel = new ViewModelProvider(this).get(BeerViewModel.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.loading));
        ((MainActivity) requireActivity()).setTitleActionBar(getString(R.string.title_beer_list));
        viewModel.init();
        beerAdapter = new BeerAdapter(getActivity());
        binding.rvBeers.setAdapter(beerAdapter);
        viewModel.getLiveDataBeer().observe(getActivity(), new Observer<Beer[]>() {
            @Override
            public void onChanged(Beer[] beers) {
                progressDialog.dismiss();
                if (beers != null){
                    Log.i("beers", Arrays.toString(beers));
                    beerAdapter.addBeers(beers);
                }
                else{
                    //Notificar que no hay internet
                }

            }
        });

        binding.rvBeers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
                int lastItem  = layoutManager.findLastCompletelyVisibleItemPosition();
                int currentTotalCount = layoutManager.getItemCount();

                if(currentTotalCount <= lastItem + 2){
                   page++;
                   loadBeers();
                   System.out.println(page);
                }


            }
        });
        loadBeers();
        return binding.getRoot();
    }



    public void loadBeers(){
        progressDialog.show();
        viewModel.getBeersApi(page);
    }
}