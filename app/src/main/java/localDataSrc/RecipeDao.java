package localDataSrc;


import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import remoteDataSource.Recipe;

@Dao
public interface RecipeDao
{
    @Insert
    void insert(Recipe note);

    @Update
    void update(Recipe note);

    @Delete
    void delete(Recipe note);



    @Query("SELECT * FROM recipies_table ")
    LiveData<List<Recipe>> getAllRecipies();


}
