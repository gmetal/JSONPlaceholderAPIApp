package com.github.gmetal.jsonplaceholderapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Comment> mComments;
    private final LayoutInflater mInflater;

    public CommentsAdapter(Context context, List<Comment> comments) {

        mContext = context;
        mComments = comments;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {

        return mComments.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        final View view = mInflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Comment currentComment = mComments.get(position);
        holder.name.setText(currentComment.getName());
        holder.email.setText(currentComment.getEmail());
        holder.body.setText(currentComment.getBody());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        TextView body;

        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.commentName);
            email = itemView.findViewById(R.id.commentEmail);
            body = itemView.findViewById(R.id.commentBody);
        }
    }
}
