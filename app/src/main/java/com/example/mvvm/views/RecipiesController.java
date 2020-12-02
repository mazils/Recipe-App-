package com.example.mvvm.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import adapters.RecipeListAdapter;
import localDataSrc.Notes;
import remoteDataSource.Recipe;
import remoteDataSource.RecipeResponse;
import viewModels.ViewModelActivity;

public class RecipiesController extends AppCompatActivity
{
    TextView name, mail;
    MenuItem logout;
    Button getRecipies;
    TextView textViewRecipies;
    ViewModelActivity viewModelActivity;
    RecipeListAdapter recipeListAdapter;
    RecyclerView recipeList;
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.collection:
                //DO SOMETHING//todo show local dbs
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
        name = findViewById(R.id.name);
        mail = findViewById(R.id.email);




        //observing live list data for notes
        viewModelActivity.getRecipesLiveData().observe(this, new Observer<RecipeResponse>() {

            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                Log.i("recipeResponse",recipeResponse.getResults().toString());
                recipeListAdapter = new RecipeListAdapter(recipeResponse.getResults());
                recipeList.setAdapter(recipeListAdapter);
//                textViewRecipies.setText("");
//                for (Recipe recipe : recipeResponse.getResults()) {
//                    textViewRecipies.append(recipe + "\n");
//                }
            }
        });






    }



        public void getRecipies(View view)
        {
            viewModelActivity.getRecipes();
        }

//    @Override
//    protected void onPause () {
//        super.onPause();
//        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("name", textViewNotes.getText().toString());
//        editor.apply();
//    }
//
//    @Override
//    protected void onResume () {
//        super.onResume();
//        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
//        String name = preferences.getString("name", "default name");
//        textViewNotes.setText(name);
//    }
//
//    public void addNote(View view)
//    {
//        viewModelActivity.addNote(editText.getText().toString());
//    }
//
//    public void deleteAll(View view)
//    {
//        viewModelActivity.deleteAllNotes();
//    }









}
