package remoteDataSource;
/**Singleton for creating our API*/
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//retrofit
public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RecipesAPI recipesAPI = retrofit.create(RecipesAPI.class);

    public static RecipesAPI getRecipesAPIApi() {
        return recipesAPI;
    }

}

