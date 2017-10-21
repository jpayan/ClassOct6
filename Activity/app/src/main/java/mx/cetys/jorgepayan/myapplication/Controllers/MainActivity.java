package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Comment;
import mx.cetys.jorgepayan.myapplication.Models.Post;
import mx.cetys.jorgepayan.myapplication.R;
import mx.cetys.jorgepayan.myapplication.Utils.CommentHelper;
import mx.cetys.jorgepayan.myapplication.Utils.PostHelper;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_KEY = "Message";
    CommentHelper commentHelper;
    PostHelper postHelper;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_getPosts = (Button) findViewById(R.id.button_getPosts);
        Button button_showPosts = (Button) findViewById(R.id.button_showPosts);
        Button button_getComments = (Button) findViewById(R.id.button_getComments);
        Button button_showComments = (Button) findViewById(R.id.button_showComments);

        commentHelper = new CommentHelper(getApplicationContext());
        postHelper = new PostHelper(getApplicationContext());

        final RequestQueue queue = Volley.newRequestQueue(this);


        button_getPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast("Synchronizing posts...");
                String postsURL = "http://jsonplaceholder.typicode.com/posts";
                JsonArrayRequest postRequest = new JsonArrayRequest(
                    Request.Method.GET, postsURL, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                System.out.println(response);
                                postHelper.open();
                                postHelper.clearTable();
                                postHelper.close();
                                for(int i = 0; i < response.length(); i++){
                                    JSONObject postJson = response.getJSONObject(i);
                                    Post post = new Post(postJson);
                                    postHelper.open();
                                    postHelper.addPost(post.getUserId(), post.getId(),
                                            post.getTitle(), post.getBody());
                                    postHelper.close();
                                }
                                makeToast("Posts synchronized.");
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
                );
                queue.add(postRequest);
            }
        });

        button_showPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postHelper.open();
                ArrayList<Post> posts = postHelper.getPosts();
                postHelper.close();
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                for(Post post : posts) {
                    post.setComments(getPostComments(post.getId()));
                }
                intent.putExtra(EXTRA_KEY, posts);
                startActivity(intent);
            }
        });

        button_getComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast("Synchronizing comments...");
                String commentsURL = "http://jsonplaceholder.typicode.com/comments";
                JsonArrayRequest commentsRequest = new JsonArrayRequest(
                        Request.Method.GET, commentsURL, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    commentHelper.open();
                                    commentHelper.clearTable();
                                    commentHelper.close();
                                    for(int i = 0; i < response.length(); i++){
                                        JSONObject commentJson = response.getJSONObject(i);
                                        Comment comment = new Comment(commentJson);
                                        commentHelper.open();
                                        commentHelper.addComment(comment.getPostId(), comment.getId(),
                                                comment.getName(), comment.getEmail(), comment.getBody());
                                        commentHelper.close();
                                    }
                                    makeToast("Comments synchronized.");
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                queue.add(commentsRequest);
            }
        });

        button_showComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentHelper.open();
                ArrayList<Comment> comments = commentHelper.getComments();
                commentHelper.close();
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                intent.putExtra(EXTRA_KEY, comments);
                startActivity(intent);
            }
        });
    }

    private void makeToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private ArrayList<Comment> getPostComments(int postId) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        commentHelper = new CommentHelper(getApplicationContext());
        comments = commentHelper.getCommentByPost(postId);
        return comments;
    }
}
