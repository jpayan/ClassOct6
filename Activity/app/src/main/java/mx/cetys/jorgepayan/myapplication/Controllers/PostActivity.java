package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Post;
import mx.cetys.jorgepayan.myapplication.R;

public class PostActivity extends AppCompatActivity {

//    private PostAdapter postAdapter;
//    private ListView listView;
    ExpandableListView listView;
    ExpandableListAdapter postAdapter;
    private ArrayList<Post> posts = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        posts = intent.getParcelableArrayListExtra(MainActivity.EXTRA_KEY);

        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        postAdapter = new PostAdapter(this, posts);
        listView.setAdapter(postAdapter);

//        fillTurnView(posts);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

//    private void fillTurnView(ArrayList<Post> postList) {
//        postAdapter.clear();
//
//        for(Post post : postList) {
//            postAdapter.add(post);
//        }
//    }
}
