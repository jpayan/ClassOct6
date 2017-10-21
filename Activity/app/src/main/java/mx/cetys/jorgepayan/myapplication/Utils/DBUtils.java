package mx.cetys.jorgepayan.myapplication.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jorge.payan on 10/6/17.
 */

public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jsonplaceholder.db";
    public static final int DATABASE_VERSION = 1;

    public static final String POST_TABLE_NAME = "POST";
    public static final String POST_USER_ID = "userId";
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "title";
    public static final String POST_BODY = "body";

    public static final String CREATE_POST_TABLE =
        "CREATE TABLE " + POST_TABLE_NAME + "(" +
            POST_USER_ID + " integer not null, " +
            POST_ID + " integer not null, " +
            POST_TITLE + " text not null, " +
            POST_BODY + " text not null, " +
        ")";

    public static final String COMMENT_TABLE_NAME = "COMMENT";
    public static final String COMMENT_POST_ID = "postId";
    public static final String COMMENT_ID = "id";
    public static final String COMMENT_NAME = "name";
    public static final String COMMENT_EMAIL = "email";
    public static final String COMMENT_BODY = "body";

    public static final String CREATE_COMMENT_TABLE =
        "CREATE TABLE " + COMMENT_TABLE_NAME + "(" +
            COMMENT_POST_ID + " integer not null, " +
            COMMENT_ID + " integer not null, " +
            COMMENT_NAME + " text not null, " +
            COMMENT_EMAIL + " text not null, " +
            COMMENT_BODY + " text not null" +
        ")";



    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_POST_TABLE);
        sqLiteDatabase.execSQL(CREATE_COMMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + POST_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COMMENT_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
