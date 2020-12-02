package repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import localDataSrc.NoteDao;
import localDataSrc.NoteDatabase;
import localDataSrc.Notes;
import remoteDataSource.RecipeResponse;
import remoteDataSource.RecipesAPI;
import remoteDataSource.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository
{
    private MutableLiveData<List<Notes>> notes;
    private static Repository instance;

    MutableLiveData<RecipeResponse> recipeResponseLiveData;

    private Repository(Application application) {

        NoteDatabase database = NoteDatabase.getInstance(application);
        NoteDao noteDao = database.noteDao();
        LiveData<List<Notes>> allNotes = noteDao.getAllNotes();




        //setting notes to empty array so it wouldnt be null
        notes = new MutableLiveData<>();
        List<Notes> newList = new ArrayList<>();
        notes.setValue(newList);


        recipeResponseLiveData = new MutableLiveData<>();
        RecipeResponse newListRecipes = new RecipeResponse();
        recipeResponseLiveData.setValue(newListRecipes);

    }
    public static synchronized Repository getInstance(Application application){

        if(instance == null)
            instance = new Repository(application);

        return instance;
    }


    public LiveData<List<Notes>> getAllNotes(){
        return notes;
    }

//    public void insert(Notes note) {
//        new InsertNoteAsync(noteDao).execute(note);
//    }//todo create async to get data from local dbs

//live data list
    public LiveData<List<Notes>> getNotes() {
        return notes;
        /*Returned to the View as LiveData to make the observed data immutable in the Activity/Fragment*/
    }

    public void addNote(String note) {
        List<Notes> curentNotes = notes.getValue();
        curentNotes.add(new Notes(note));
        notes.setValue(curentNotes);
        /*Changes the value of the MutableLiveData and notifies any observers*/
    }

    public void deleteAllNotes() {
        List<Notes> curentNotes = notes.getValue();
        curentNotes.clear();
        notes.setValue(curentNotes);
    }


/**Use your ServiceGenerator to create the API.
 Respond in callback. Remember to check HTTP-response code.
 call.enqueue() - asynchronous
 call.execute() - synchronous

 */

public void requestRecipes()
{
    RecipesAPI recipesAPI = ServiceGenerator.getRecipesAPIApi();
    Call<RecipeResponse> call = recipesAPI.getRecipes();
    call.enqueue(new Callback<RecipeResponse>() {


        @Override
        public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
            if (response.code() == 200)
            {
                recipeResponseLiveData.setValue(response.body());
            }
        }

        @Override
        public void onFailure(Call<RecipeResponse> call, Throwable t) {
            Log.i("bybis gaunas",t.getMessage());

        }
    });
}







    public void getRecipies()
    {
        requestRecipes();

    }

    public LiveData<RecipeResponse> getRecipiesLiveData()
    {
    return recipeResponseLiveData;
    }
}


