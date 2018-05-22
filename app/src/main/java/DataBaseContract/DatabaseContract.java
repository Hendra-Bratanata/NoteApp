package DataBaseContract;
/**

 kelas ini digunakan untuk memudahkan akses ke nama tabel dan field


 */


import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
public static String TABLE_NOTE = "note";

public static final class NoteColumns implements BaseColumns{
//    note title
   public static String TITLE ="title";
//    note Desc
    public static String DESC = "deskripsion";
//    note Date
    public static String DATE = "date";

}
    public static final String AUTHORITY = "com.ichirotech.bratanata.mynoteapp";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NOTE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}
