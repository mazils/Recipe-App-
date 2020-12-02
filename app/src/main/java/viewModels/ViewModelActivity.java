package viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import localDataSrc.Notes;
import remoteDataSource.RecipeResponse;
import repository.Repository;

public class ViewModelActivity extends AndroidViewModel {

    Repository  repository;

    public ViewModelActivity(Application application) {
        super(application);
        repository=  Repository.getInstance(application);
    }

   public LiveData<List<Notes>> getNotes() {
       return repository.getNotes();
       /*Returned to the View as LiveData to make the observed data immutable in the Activity/Fragment*/
   }

   public void addNote(String note) {

       repository.addNote(note);
       /*Changes the value of the MutableLiveData and notifies any observers*/
   }

   public void getRecipes()
   {
        repository.getRecipies();
   }


   public void deleteAllNotes() {
       repository.deleteAllNotes();
    }

    public LiveData<RecipeResponse> getRecipesLiveData() {
        return repository.getRecipiesLiveData();
    }
}
