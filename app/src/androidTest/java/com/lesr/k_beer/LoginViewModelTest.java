package com.lesr.k_beer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.viewModel.LoginViewModel;

import org.junit.Before;
import org.junit.Test;

public class LoginViewModelTest {

    Context context;

    private LoginViewModel viewModel;
    String testName = "Luis Enrique";
    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        viewModel = new LoginViewModel();
        viewModel.init(context);
    }

    @Test
    public void  testAddNamePreferencesAndGetIt(){
        viewModel.addName(testName);
        assertEquals(testName,viewModel.getNamepreference());
    }
}
