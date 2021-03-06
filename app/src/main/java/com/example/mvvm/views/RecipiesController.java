package com.example.mvvm.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.google.firebase.auth.FirebaseAuth;

import adapters.RecipeListAdapter;
import remoteDataSource.RecipeResponse;
import viewModels.ViewModelActivity;

public class RecipiesController extends AppCompatActivity {
    MenuItem logout;
    Button getRecipies;
    ViewModelActivity viewModelActivity;
    RecipeListAdapter recipeListAdapter;
    RecyclerView recipeList;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.collection:
                Intent intentSavedRecipies = new Intent(getApplicationContext(), SavedRecipiesController.class);
                startActivity(intentSavedRecipies);
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), AuthentificationController.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipies);
        getRecipies = findViewById(R.id.getRecipiesButton);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class); // getting instance of VM
        recipeList = findViewById(R.id.recycleView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = findViewById(R.id.menu);
        setSupportActionBar(toolbar);
        logout = findViewById(R.id.logout);

        Bundle bundle = getIntent().getExtras();
//        userPhoto = findViewById(R.id.userPhoto);//todo display ohoto of the user
        Toast.makeText(this, getString(R.string.welcomeToast) + " " + bundle.getString("name"), Toast.LENGTH_SHORT).show();


        //observing live list data for notes
        viewModelActivity.getRecipesLiveData().observe(this, new Observer<RecipeResponse>() {

            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                Log.i("recipeResponse", recipeResponse.getResults().toString());
                recipeListAdapter = new RecipeListAdapter(recipeResponse.getResults(), getApplication());
                recipeList.setAdapter(recipeListAdapter);
            }
        });


    }


    public void getRecipies(View view) {

        viewModelActivity.getRecipes();
    }


}
