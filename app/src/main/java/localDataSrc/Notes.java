package localDataSrc;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")//call it whatever table
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String note;



    public Notes( String note) {
        this.note = note;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
