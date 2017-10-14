package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mx.cetys.jorgepayan.myapplication.Models.Comment;
import mx.cetys.jorgepayan.myapplication.R;

/**
 * Created by jorge.payan on 10/13/17.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {

    public CommentAdapter(Context context){
        super(context, R.layout.comment_row, R.id.text_view_comment_id);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);

        TextView text_view_postId = (TextView) oView.findViewById(R.id.text_view_postId);
        TextView text_view_id = (TextView) oView.findViewById(R.id.text_view_comment_id);
        TextView text_view_name = (TextView) oView.findViewById(R.id.text_view_name);
        TextView text_view_email = (TextView) oView.findViewById(R.id.text_view_email);
        TextView text_view_body = (TextView) oView.findViewById(R.id.text_view_commentBody);

        Comment comment = this.getItem(position);

        text_view_postId.setText("Post Id:   " + comment.getPostId());
        text_view_id.setText("Id:   " + comment.getId());
        text_view_name.setText("Name:   " + comment.getName());
        text_view_email.setText("Email:   " + comment.getEmail());
        text_view_body.setText("Body:   " + comment.getBody());

        return oView;
    }
}
