package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import mx.cetys.jorgepayan.myapplication.Models.Comment;
import mx.cetys.jorgepayan.myapplication.R;

public class CommentActivity extends AppCompatActivity {

    private CommentAdapter commentAdapter;
    private ListView listView;
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.list_view_comments);
        commentAdapter = new CommentAdapter(this);
        listView.setAdapter(commentAdapter);

        comments = intent.getParcelableArrayListExtra(MainActivity.EXTRA_KEY);
        fillTurnView(comments);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void fillTurnView(ArrayList<Comment> commentList) {
        commentAdapter.clear();

        for(Comment comment : commentList) {
            commentAdapter.add(comment);
        }
    }
}
