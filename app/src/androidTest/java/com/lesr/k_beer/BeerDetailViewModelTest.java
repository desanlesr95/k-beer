package com.lesr.k_beer;

import static org.junit.Assert.assertNotNull;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.lesr.k_beer.model.Ingredients;
import com.lesr.k_beer.viewModel.BeerDetailViewModel;
;import org.junit.Before;
import org.junit.Test;
public class BeerDetailViewModelTest {
    Context context;

    private BeerDetailViewModel viewModel;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        viewModel = new BeerDetailViewModel();
        viewModel.init(context);
    }

    @Test
    public void  testGetIngredientsInValidID(){
        int id = 1;
        Ingredients ingredients = viewModel.getIngredients(id);
        System.out.println("Valor: "+ingredients);
        assertNotNull(ingredients);
    }

}
