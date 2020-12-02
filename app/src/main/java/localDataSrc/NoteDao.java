package localDataSrc;


import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao
{
    @Insert
    void insert(Notes note);

    @Update
    void update(Notes note);

    @Delete
    void delete(Notes note);



    @Query("SELECT * FROM note_table ")
    LiveData<List<Notes>> getAllNotes();


}
