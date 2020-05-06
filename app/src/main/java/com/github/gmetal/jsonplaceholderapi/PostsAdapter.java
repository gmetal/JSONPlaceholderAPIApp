package com.github.gmetal.jsonplaceholderapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final List<Post> mData;
    private final View.OnClickListener mOnClickListener;

    public PostsAdapter(final Context context, final List<Post> posts, final View.OnClickListener onClickListener) {

        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = posts;
        mOnClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        final View view = mInflater.inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Post currentPost = mData.get(position);
        holder.postTitle.setText(currentPost.getTitle());
        holder.postBody.setText(currentPost.getBody());

        holder.itemView.setTag(currentPost);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle;
        TextView postBody;

        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            postTitle = itemView.findViewById(R.id.postTitle);
            postBody = itemView.findViewById(R.id.postBody);
        }
    }
}
