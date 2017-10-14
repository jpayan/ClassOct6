package mx.cetys.jorgepayan.myapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Comment;

/**
 * Created by jorge.payan on 10/6/17.
 */

public class CommentHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] COMMENT_TABLE_COLUMNS = {
        DBUtils.COMMENT_POST_ID,
        DBUtils.COMMENT_ID,
        DBUtils.COMMENT_NAME,
        DBUtils.COMMENT_EMAIL,
        DBUtils.COMMENT_BODY
    };

    public CommentHelper(Context context) {
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.COMMENT_TABLE_NAME, COMMENT_TABLE_COLUMNS,
                null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            comments.add(parseComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return comments;
    }

    public Comment getComment(int commentId) {
        Cursor cursor = database.query(DBUtils.COMMENT_TABLE_NAME, COMMENT_TABLE_COLUMNS,
                DBUtils.COMMENT_ID + " = " + commentId, null, null, null, null);

        cursor.moveToFirst();
        Comment comment = parseComment(cursor);
        cursor.close();

        return comment;
    }

    public void addComment(int postId, int id, String name, String email, String body) {
        ContentValues values = new ContentValues();

        values.put(DBUtils.COMMENT_POST_ID, postId);
        values.put(DBUtils.COMMENT_ID, id);
        values.put(DBUtils.COMMENT_NAME, name);
        values.put(DBUtils.COMMENT_EMAIL, email);
        values.put(DBUtils.COMMENT_BODY, body);

        database.insert(DBUtils.COMMENT_TABLE_NAME, null, values);
    }

    public void updateComment(Comment comment) {
        ContentValues values = new ContentValues();

        values.put(DBUtils.COMMENT_POST_ID, comment.getPostId());
        values.put(DBUtils.COMMENT_ID, comment.getId());
        values.put(DBUtils.COMMENT_NAME, comment.getName());
        values.put(DBUtils.COMMENT_EMAIL, comment.getEmail());
        values.put(DBUtils.COMMENT_BODY, comment.getBody());

        database.update(DBUtils.COMMENT_TABLE_NAME, values, DBUtils.COMMENT_ID + " = " + comment.getId(), null);
    }

    public void deleteComment(int commentId) {
        database.delete(DBUtils.COMMENT_TABLE_NAME, DBUtils.COMMENT_ID + " = " + commentId, null);
    }

    public void clearTable() {
        database.execSQL("DELETE FROM " + DBUtils.COMMENT_TABLE_NAME);
    }

    private Comment parseComment(Cursor cursor) {
        int postId = cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENT_POST_ID));
        int id = cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_NAME));
        String email = cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_EMAIL));
        String body = cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_BODY));

        Comment comment = new Comment(postId, id, name, email, body);

        return comment;
    }
}
