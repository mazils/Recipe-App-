package com.example.mvvm.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;

import java.util.ArrayList;
import java.util.List;

import adapters.SavedRecipiesAdapter;
import remoteDataSource.Recipe;
import viewModels.ViewModelActivity;

public class SavedRecipiesController extends AppCompatActivity {


    private ViewModelActivity viewModelActivity;
    private RecyclerView recipeList;

    private SavedRecipiesAdapter savedRecipiesAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedrecipies);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class); // getting instance of VM
        recipeList = findViewById(R.id.savedRecipies);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(this));
        savedRecipiesAdapter = new SavedRecipiesAdapter(new ArrayList<Recipe>() ,getApplication());
        recipeList.setAdapter(savedRecipiesAdapter);

        viewModelActivity.getAllRecipies().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                savedRecipiesAdapter.setRecipeList(recipes);
                savedRecipiesAdapter.notifyDataSetChanged();
            }
        });
    }
}
