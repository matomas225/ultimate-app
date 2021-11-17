package com.example.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;

    ArrayList<PostActivity> list;

    public ListAdapter(Context context, ArrayList<PostActivity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_activity, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        PostActivity post = list.get(position);
        holder.postTitle.setText(post.getPostTitle());
        holder.postDescription.setText(post.getPostDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListViewHolder extends  RecyclerView.ViewHolder{

        TextView postTitle, postDescription;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.postTitle);

            postDescription = itemView.findViewById(R.id.postDescription);

        }
    }
}
