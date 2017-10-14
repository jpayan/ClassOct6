package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mx.cetys.jorgepayan.myapplication.Models.Comment;
import mx.cetys.jorgepayan.myapplication.Models.Post;
import mx.cetys.jorgepayan.myapplication.R;

/**
 * Created by jorge.payan on 10/13/17.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context){
        super(context, R.layout.post_row, R.id.text_view_post_id);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);

        TextView text_view_userId = (TextView) oView.findViewById(R.id.text_view_userId);
        TextView text_view_id = (TextView) oView.findViewById(R.id.text_view_post_id);
        TextView text_view_title = (TextView) oView.findViewById(R.id.text_view_title);
        TextView text_view_body = (TextView) oView.findViewById(R.id.text_view_postBody);

        Post post = this.getItem(position);

        text_view_userId.setText("User Id:   " + post.getUserId());
        text_view_id.setText("Id:   " + post.getId());
        text_view_title.setText("Title:   " + post.getTitle());
        text_view_body.setText("Body:   " + post.getBody());

        return oView;
    }

}
