package mx.cetys.jorgepayan.myapplication.Controllers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.cetys.jorgepayan.myapplication.Models.Comment;
import mx.cetys.jorgepayan.myapplication.Models.Post;
import mx.cetys.jorgepayan.myapplication.R;
import mx.cetys.jorgepayan.myapplication.Utils.CommentHelper;

/**
 * Created by jorge.payan on 10/13/17.
 */

//public class PostAdapter extends ArrayAdapter<Post> {
public class PostAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.posts.get(groupPosition).getComments().get(childPosition);
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, null);
        }

        Comment comment = this.posts.get(groupPosition).getComments().get(childPosition);
        TextView childTextView = view.findViewById(R.id.expandedListItem);
        childTextView.setText("Body: " + comment.getBody() + "\nId: " + comment.getId() + "\nEmail: " + comment.getEmail());
        return view;
    }


    @Override
    public int getChildrenCount(int i) {
        return this.posts.get(i).getComments().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return this.posts.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group, null);
        }

        Post item = this.posts.get(i);
        TextView header = view.findViewById(R.id.listTitle);
        header.setText("Title: " + item.getTitle() + "\nId: " + item.getId());
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
