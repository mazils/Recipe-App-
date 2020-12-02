package remoteDataSource;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeResponse {

    private ArrayList<Recipe> results;

    public RecipeResponse() {
        results = new ArrayList<>();
    }

    public ArrayList<Recipe> getResults() {
        return results;
    }

    public void setResults(ArrayList<Recipe> results) {
        this.results = results;
    }
}
