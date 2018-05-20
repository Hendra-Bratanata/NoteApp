package DataBaseContract;
/**
 * kelas ini digunakan untuk pencptaan database berikut dengan table nya
 *
 *
 *
 *
 * */
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static DataBaseContract.DatabaseContract.TABLE_NOTE;
import static DataBaseContract.DatabaseContract.NoteColumns.TITLE;
import static DataBaseContract.DatabaseContract.NoteColumns._ID;
import static DataBaseContract.DatabaseContract.NoteColumns.DATE;
import static DataBaseContract.DatabaseContract.NoteColumns.DESC;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbnoteapp";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE=String.format("CREATE TABLE %s"
    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
    +" %s TEXT  NOT NULL,"
    + "%s TEXT NOT NULL,"
    +" %s TEXT NOT NULL)",
            TABLE_NOTE,_ID,TITLE,DESC,DATE
            );

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
    onCreate(db);
    }
}
