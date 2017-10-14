package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Post;
import mx.cetys.jorgepayan.myapplication.R;

public class PostActivity extends AppCompatActivity {

    private PostAdapter postAdapter;
    private ListView listView;
    private ArrayList<Post> posts = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.list_view_posts);
        postAdapter = new PostAdapter(this);
        listView.setAdapter(postAdapter);

        posts = intent.getParcelableArrayListExtra(MainActivity.EXTRA_KEY);
        fillTurnView(posts);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void fillTurnView(ArrayList<Post> postList) {
        postAdapter.clear();

        for(Post post : postList) {
            postAdapter.add(post);
        }
    }
}
