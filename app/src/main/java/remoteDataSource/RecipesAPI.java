package remoteDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesAPI {
    @GET("https://api.spoonacular.com/recipes/complexSearch?apiKey=e78b5d57a36842e285add78d53923ff5")
    Call<RecipeResponse> getRecipes();//call is asynhronious thingy
}



//e78b5d57a36842e285add78d53923ff5 API KEY