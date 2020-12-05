package repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import localDataSrc.RecipeDao;
import localDataSrc.RecipeDatabase;
import remoteDataSource.Recipe;
import remoteDataSource.RecipeResponse;
import remoteDataSource.RecipesAPI;
import remoteDataSource.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository instance;
    MutableLiveData<RecipeResponse> recipeResponseLiveData;
    private RecipeDao recipeDao;
    private LiveData<Recipe> recipeLiveData;

    private Repository(Application application) {

        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(application);
        recipeDao = recipeDatabase.recipeDao();


        recipeResponseLiveData = new MutableLiveData<>();
        RecipeResponse newListRecipes = new RecipeResponse();
        recipeResponseLiveData.setValue(newListRecipes);

    }

    public static synchronized Repository getInstance(Application application) {

        if (instance == null)
            instance = new Repository(application);

        return instance;
    }


    /**
     * Use your ServiceGenerator to create the API.
     * Respond in callback. Remember to check HTTP-response code.
     * call.enqueue() - asynchronous
     * call.execute() - synchronous
     */

    public void requestRecipes() {
        RecipesAPI recipesAPI = ServiceGenerator.getRecipesAPIApi();
        Call<RecipeResponse> call = recipesAPI.getRecipes();
        call.enqueue(new Callback<RecipeResponse>() {


            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.code() == 200) {
                    recipeResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i("bybis gaunas", t.getMessage());

            }
        });
    }


    public void getRecipies() {
        requestRecipes();

    }

    public LiveData<RecipeResponse> getRecipiesLiveData() {
        return recipeResponseLiveData;
    }


    public void addRecipe(Recipe recipe) {
        try {
            RecipeDatabase.executor.execute(() -> recipeDao.insert(recipe));//execute creates new thread and calls insert in a new thread //todo handle the exception
        } catch (Exception d) {
            System.out.println(d.getMessage());
        }

    }

    public LiveData<List<Recipe>> getAllRecipies() {
        return recipeDao.getAllRecipies();
    }

    public void deleteRecipe(Recipe recipe) {
        RecipeDatabase.executor.execute(() -> recipeDao.delete(recipe));
    }

}


