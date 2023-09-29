package com.lesr.k_beer.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.FragmentIngredientsBinding;
import com.lesr.k_beer.model.Hops;
import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.model.Malt;
import com.lesr.k_beer.util.Constants;
import com.lesr.k_beer.view.MainActivity;

public class IngredientsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    FragmentIngredientsBinding binding;
    Ingredients ingredients;
    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ingredients,container,false);
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(Constants.INGREDIENT)) {

                ingredients = args.getParcelable(Constants.INGREDIENT);
                System.out.println(ingredients);
                binding.setHops(ingredients.hops.toArray(new Hops[0]));
                binding.setMalts(ingredients.malt.toArray(new Malt[0]));
                ((MainActivity) requireActivity()).setTitleActionBar("Ingredientes");
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
        return binding.getRoot();
    }
}