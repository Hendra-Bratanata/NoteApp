package entity;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;



import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns._ID;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.DATE;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.DESC;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.TITLE;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.getColumnInt;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.getColumnString;

public class Note implements Parcelable {

    private int id;
    private String title;
    private String decs;
    private String date;

    public Note() {
    }
    public Note(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, TITLE);
        this.decs = getColumnString(cursor, DESC);
        this.date = getColumnString(cursor, DATE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.decs);
        dest.writeString(this.date);
    }

    protected Note(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.decs = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
