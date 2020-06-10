package com.github.gmetal.jsonplaceholderapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    public static final String POST_ID_EXTRA = "post_id";
    public static final String USER_ID_EXTRA = "user_id";

    private ProgressDialog progressDialog;
    private TextView postIdTextView;
    private Button viewUserDetailsButtton;
    private TextView titleTextView;
    private TextView bodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final int postId = getIntent().getIntExtra(POST_ID_EXTRA, 0);
        final int userId = getIntent().getIntExtra(USER_ID_EXTRA, 0);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading post...");
        progressDialog.show();

        postIdTextView = findViewById(R.id.postIdLabel);
        viewUserDetailsButtton = findViewById(R.id.viewUserDetails);
        titleTextView = findViewById(R.id.postTitle);
        bodyTextView = findViewById(R.id.postBody);

        final Button viewCommentsButton = findViewById(R.id.viewCommentsButton);
        viewCommentsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                Intent intent = new Intent(PostActivity.this, PostCommentsActivity.class);
                intent.putExtra(PostCommentsActivity.POST_ID_EXTRA, postId);
                startActivity(intent);
            }
        });

        viewUserDetailsButtton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(final View v) {

                        Intent intent = new Intent(PostActivity.this, UserActivity.class);
                        intent.putExtra(UserActivity.USER_ID_EXTRA, userId);
                        startActivity(intent);
                    }
                }
        );

        JSONPlaceholderService serviceInstance = RetrofitClientSingleton.getInstance().getRetrofit().create(JSONPlaceholderService.class);
        final Call<Post> postById = serviceInstance.getPostById(postId);
        postById.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(final Call<Post> call, final Response<Post> response) {

                progressDialog.dismiss();
                final Post post = response.body();
                updateUi(post);
            }

            @Override
            public void onFailure(final Call<Post> call, final Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(PostActivity.this, "Failed to retrieve Post by id", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUi(final Post post) {

        postIdTextView.setText(getString(R.string.post_id_label, post.getId()));
        viewUserDetailsButtton.setText(getString(R.string.user_id_label, post.getUserId()));
        titleTextView.setText(getString(R.string.title_label, post.getTitle()));
        bodyTextView.setText(getString(R.string.body_label, post.getBody()));
    }
}
