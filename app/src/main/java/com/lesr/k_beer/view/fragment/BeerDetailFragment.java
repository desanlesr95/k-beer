package com.lesr.k_beer.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.FragmentBeerDetailBinding;
import com.lesr.k_beer.model.Beer;
import com.lesr.k_beer.util.Constants;
import com.lesr.k_beer.view.MainActivity;
import com.lesr.k_beer.viewModel.BeerDetailViewModel;


public class BeerDetailFragment extends Fragment {
    FragmentBeerDetailBinding binding;
    BeerDetailViewModel viewModel;
    Beer beer;


    public BeerDetailFragment() {

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
        viewModel =  new ViewModelProvider(getActivity()).get(BeerDetailViewModel.class);
        viewModel.init(getActivity());
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(Constants.BEER_DETAIL)) {
                beer = args.getParcelable(Constants.BEER_DETAIL);
                System.out.println(beer);
                if(args.getInt(Constants.STRATEGY) == 1){
                    beer.ingredients = viewModel.getIngredients(beer.id);
                }
                ((MainActivity) requireActivity()).setTitleActionBar(beer.getName());
                binding.setData(beer);
            }
        }
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_controller);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Toolbar toolbar = ((MainActivity) requireActivity()).findViewById(R.id.toolbar);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navController.navigateUp();
                }
            });
        }
        binding.btnIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.INGREDIENT,beer.getIngredients());
                navController.navigate(R.id.action_fragment_beerDetail_to_fragment_ingredients,bundle);
            }
        });

        return binding.getRoot();
    }
}