package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> localDataSet;
    private String user_id;
    private Context context;

    // This is the array of post objects
    public PostAdapter(List<Post> dataSet, String user_id, Context context) {
        this.localDataSet = dataSet;
        this.user_id = user_id;
        this.context = context;
    }

    // This is to get the template unique ID fields to be replaced with later
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username;
        private final TextView postTitle;
        private final TextView postContent;
        private final Button postLikes;
        private final Button postDislikes;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            username = (TextView) view.findViewById(R.id.username);
            postTitle = (TextView) view.findViewById(R.id.postTitle);
            postContent = (TextView) view.findViewById(R.id.postContent);
            postLikes = (Button) view.findViewById(R.id.likeButton);
            postDislikes = (Button) view.findViewById(R.id.dislikeButton);
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

        public Button getPostLikes() {
            return postLikes;
        }

        public Button getPostDislikes() {
            return postDislikes;
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
        viewHolder.getPostLikes().setText(localDataSet.get(position).getPost_likes() + "");
        viewHolder.getPostDislikes().setText(localDataSet.get(position).getPost_dislikes() + "");

        viewHolder.postLikes.setOnClickListener(view -> {
            // Update database when liked
            // GET which post you are liking
            int post_id = localDataSet.get(viewHolder.getAdapterPosition()).getPost_id();

            DatabaseImpressions DBI = new DatabaseImpressions(context);
            DBI.modifyLikes(post_id, Integer.parseInt(user_id));

            // Update button view
            viewHolder.getPostLikes().setText(localDataSet.get(position).getPost_likes() + "");
            viewHolder.getPostDislikes().setText(localDataSet.get(position).getPost_dislikes() + "");

            Log.d("NEWLIKES", "onBindViewHolder: " + localDataSet.get(position).getPost_likes());
            Log.d("NEWDISLIKES", "onBindViewHolder: " + localDataSet.get(position).getPost_dislikes());
        });
        viewHolder.postDislikes.setOnClickListener(view -> {
            // Update database when disliked
            int post_id = localDataSet.get(viewHolder.getAdapterPosition()).getPost_id();

            DatabaseImpressions DBI = new DatabaseImpressions(context);
            DBI.modifyDislikes(post_id, Integer.parseInt(user_id));

            // Update button view
            viewHolder.getPostLikes().setText(localDataSet.get(position).getPost_likes() + "");
            viewHolder.getPostDislikes().setText(localDataSet.get(position).getPost_dislikes() + "");

            Log.d("NEWLIKES", "onBindViewHolder: " + localDataSet.get(position).getPost_likes());
            Log.d("NEWDISLIKES", "onBindViewHolder: " + localDataSet.get(position).getPost_dislikes());
        });
    }

    // This just gets you the database length of post
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
