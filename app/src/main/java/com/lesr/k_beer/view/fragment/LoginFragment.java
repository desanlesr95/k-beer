package com.lesr.k_beer.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesr.k_beer.R;
import com.lesr.k_beer.databinding.FragmentLoginBinding;
import com.lesr.k_beer.view.MainActivity;
import com.lesr.k_beer.viewModel.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoginViewModel viewModel;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_controller);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.init(getContext());
        viewModel.requestNamePreference();
        ((MainActivity) requireActivity()).hideActionBarTitle();
        viewModel.getUsername().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.edtName.setText(s);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addName(binding.edtName.getText().toString());
                navController.navigate(R.id.action_fragment_login_to_fragment_beerList);
            }
        });
        return binding.getRoot();
    }
}