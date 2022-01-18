package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> localDataSet;

    // This is the array of post objects
    public PostAdapter(List<Post> dataSet) {
        this.localDataSet = dataSet;
    }

    // This is to get the template unique ID fields to be replaced with later
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username;
        private final TextView postTitle;
        private final TextView postContent;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            username = (TextView) view.findViewById(R.id.username);
            postTitle = (TextView) view.findViewById(R.id.postTitle);
            postContent = (TextView) view.findViewById(R.id.postContent);
        }

        // The following functions returns the TextView objects
        public TextView getUsername() {
            return username;
        }

        public TextView getPostTitle() {
            return postTitle;
        }

        public TextView getPostContent() {
            return postContent;
        }
    }

    // This is to inflate the layout template file with the SQLite Database length passed in as argument
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_main_posttemplate, viewGroup, false);

        return new ViewHolder(view);
    }

    // This is to override any existing text in the TextView template to be replaced with the SQLite Database contents
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getUsername().setText(localDataSet.get(position).getPost_creator());
        viewHolder.getPostTitle().setText(localDataSet.get(position).getPost_title());
        viewHolder.getPostContent().setText(localDataSet.get(position).getPost_content());
    }

    // This just gets you the database length of post
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
