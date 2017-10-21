package mx.cetys.jorgepayan.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jorge.payan on 10/6/17.
 */

public class Post implements Parcelable {
//public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
    private ArrayList<Comment> comments;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.comments = new ArrayList<Comment>();
    }

    public Post(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        comments = in.readArrayList(Comment.class.getClassLoader());
    }

    public Post(JSONObject jsonObject) {
        try {
            this.userId = jsonObject.getInt("userId");
            this.id = jsonObject.getInt("id");
            this.title = jsonObject.getString("title");
            this.body = jsonObject.getString("body");
            this.comments = new ArrayList<Comment>();
        }
        catch(JSONException e){

        }
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeList(comments);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
