package com.lesr.k_beer.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.lesr.k_beer.model.AppDatabase;
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
    int strategy = 0;
    Boolean end =false;
    public BeersFragment() {

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
        viewModel = new ViewModelProvider(this).get(BeerViewModel.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.loading));
        ((MainActivity) requireActivity()).setTitleActionBar(getString(R.string.title_beer_list));
        viewModel.init(getActivity());
        beerAdapter = new BeerAdapter(getActivity(),strategy);
        binding.rvBeers.setAdapter(beerAdapter);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        viewModel.getLiveDataBeer().observe(getActivity(), new Observer<Beer[]>() {
            @Override
            public void onChanged(Beer[] beers) {
                progressDialog.dismiss();
                Log.i("beers", Arrays.toString(beers));
                if (beers != null){
                    beerAdapter.setStrategy(strategy);
                    if (beers.length == 0){
                        end = true;
                    }
                    else{
                        beerAdapter.addBeers(beers);
                    }

                    if (strategy == 0){
                        viewModel.insertBeer(beers);
                    }
                }
                else{
                    strategy = 1;
                    beerAdapter.setStrategy(strategy);
                    viewModel.getBeersBD();
                    end = true;
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
                if(strategy == 0){
                    GridLayoutManager layoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
                    int lastItem  = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();
                    if(currentTotalCount <= lastItem + 2){
                        page++;
                        loadBeers();
                        System.out.println(page);
                    }
                }
            }
        });
        loadBeers();
        return binding.getRoot();
    }



    public void loadBeers(){
        if (!end){
            progressDialog.show();
            viewModel.getBeersApi(page);
        }
    }
}