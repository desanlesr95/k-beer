package com.lesr.k_beer.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.FragmentBeerDetailBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.util.Constants;
import com.lesr.k_beer.view.MainActivity;


public class BeerDetailFragment extends Fragment {
    FragmentBeerDetailBinding binding;
    Beer beer;


    public BeerDetailFragment() {
        // Required empty public constructor
    }

    public static BeerDetailFragment newInstance(String param1, String param2) {
        BeerDetailFragment fragment = new BeerDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_beer_detail,container,false);
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(Constants.BEER_DETAIL)) {

                beer = args.getParcelable(Constants.BEER_DETAIL);
                System.out.println(beer);
                ((MainActivity) requireActivity()).setTitleActionBar(beer.getName());
            }
        }

        return binding.getRoot();
    }
}