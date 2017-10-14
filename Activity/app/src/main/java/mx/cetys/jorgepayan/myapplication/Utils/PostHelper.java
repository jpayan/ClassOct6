package mx.cetys.jorgepayan.myapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Post;

/**
 * Created by jorge.payan on 10/6/17.
 */

public class PostHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] POST_TABLE_COLUMNS = {
            DBUtils.POST_USER_ID,
            DBUtils.POST_ID,
            DBUtils.POST_TITLE,
            DBUtils.POST_BODY
    };

    public PostHelper(Context context) {
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.POST_TABLE_NAME, POST_TABLE_COLUMNS,
                null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            posts.add(parsePost(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return posts;
    }

    public Post getPost(int postId) {
        Cursor cursor = database.query(DBUtils.POST_TABLE_NAME, POST_TABLE_COLUMNS,
                DBUtils.POST_ID + " = " + postId, null, null, null, null);

        cursor.moveToFirst();
        Post post = parsePost(cursor);
        cursor.close();

        return post;
    }

    public void addPost(int userId, int id, String title, String body) {
        ContentValues values = new ContentValues();

        values.put(DBUtils.POST_USER_ID, userId);
        values.put(DBUtils.POST_ID, id);
        values.put(DBUtils.POST_TITLE, title);
        values.put(DBUtils.POST_BODY, body);

        database.insert(DBUtils.POST_TABLE_NAME, null, values);
    }

    public void updatePost(Post post) {
        ContentValues values = new ContentValues();

        values.put(DBUtils.POST_USER_ID, post.getUserId());
        values.put(DBUtils.POST_ID, post.getId());
        values.put(DBUtils.POST_TITLE, post.getTitle());
        values.put(DBUtils.POST_BODY, post.getBody());

        database.update(DBUtils.POST_TABLE_NAME, values, DBUtils.POST_ID + " = " + post.getId(), null);
    }

    public void deletePost(int postId) {
        database.delete(DBUtils.POST_TABLE_NAME, DBUtils.POST_ID + " = " + postId, null);
    }

    public void clearTable() {
        database.execSQL("DELETE FROM " + DBUtils.POST_TABLE_NAME);
    }

    private Post parsePost(Cursor cursor) {

        int userId = cursor.getInt(cursor.getColumnIndex(DBUtils.POST_USER_ID));
        int id = cursor.getInt(cursor.getColumnIndex(DBUtils.POST_ID));
        String title = cursor.getString(cursor.getColumnIndex(DBUtils.POST_TITLE));
        String body = cursor.getString(cursor.getColumnIndex(DBUtils.POST_BODY));

        Post post = new Post(userId, id, title, body);

        return post;
    }
}
