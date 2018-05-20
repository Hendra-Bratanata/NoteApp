package DataBaseContract;
/**

 kelas ini digunakan untuk memudahkan akses ke nama tabel dan field


 */


import android.provider.BaseColumns;

public class DatabaseContract {
static String TABLE_NOTE = "note";

static final class NoteColumns implements BaseColumns{
//    note title
    static String TITLE ="title";
//    note Desc
    static String DESC = "deskripsion";
//    note Date
    static String DATE = "date";

}
}
