package com.github.gmetal.jsonplaceholderapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostCommentsActivity extends AppCompatActivity {

    public static final String POST_ID_EXTRA = "post_id";

    private ProgressDialog progressDialog;
    private RecyclerView commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comments);

        final int postId = getIntent().getIntExtra(POST_ID_EXTRA, 0);

        commentsList = findViewById(R.id.commentsList);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading comments for post: " + postId);
        progressDialog.show();

        final JSONPlaceholderService jsonPlaceholderService = RetrofitClientSingleton.getInstance().getRetrofit().create(JSONPlaceholderService.class);
        final Call<List<Comment>> commentsByPostId = jsonPlaceholderService.getCommentsByPostId(postId);
        commentsByPostId.enqueue(new Callback<List<Comment>>() {

            @Override
            public void onResponse(final Call<List<Comment>> call, final Response<List<Comment>> response) {

                progressDialog.dismiss();
                updateCommentsList(response.body());
            }

            @Override
            public void onFailure(final Call<List<Comment>> call, final Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(PostCommentsActivity.this, "Failed to retrieve list of comments", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCommentsList(final List<Comment> comments) {

        commentsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        commentsList.setAdapter(new CommentsAdapter(this, comments));
    }
}
