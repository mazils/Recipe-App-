package viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import remoteDataSource.Recipe;
import remoteDataSource.RecipeResponse;
import repository.Repository;

public class ViewModelActivity extends AndroidViewModel {

    Repository repository;

    public ViewModelActivity(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }


    public void getRecipes() {
        repository.getRecipies();
    }

    public LiveData<RecipeResponse> getRecipesLiveData() {
        return repository.getRecipiesLiveData();
    }


    public void addRecipe(Recipe recipe) {
        repository.addRecipe(recipe);
    }

    public LiveData<List<Recipe>> getAllRecipies() {
        return repository.getAllRecipies();
    }

}
