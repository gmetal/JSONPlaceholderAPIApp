package com.github.gmetal.jsonplaceholderapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Fetching data....");
        progressDialog.show();

        final RecyclerView postsList = findViewById(R.id.postsList);
        postsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        JSONPlaceholderService serviceInstance = RetrofitClientSingleton.getInstance().getRetrofit().create(JSONPlaceholderService.class);
        final Call<List<Post>> allPosts = serviceInstance.getAllPosts();
        allPosts.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(final Call<List<Post>> call, final Response<List<Post>> response) {

                final List<Post> posts = response.body();
                progressDialog.dismiss();
                postsList.setAdapter(new PostsAdapter(MainActivity.this, posts, MainActivity.this));
            }

            @Override
            public void onFailure(final Call<List<Post>> call, final Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(final View v) {

        Post currentPost = (Post) v.getTag();
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.POST_ID_EXTRA, currentPost.getId());

        startActivity(intent);
    }

    private List<Post> getFakeData() {

        List<Post> posts = new ArrayList<>();
        posts.add(
                new Post(1, 1,
                         "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                         "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
                )
        );

        posts.add(
                new Post(2, 1,
                         "qui est esse",
                         "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
                )
        );

        posts.add(
                new Post(3, 1,
                         "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                         "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"
                )
        );

        return posts;
    }
}
