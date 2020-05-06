package com.github.gmetal.jsonplaceholderapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        final RecyclerView postsList = findViewById(R.id.postsList);
        postsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        postsList.setAdapter(new PostsAdapter(this, posts, this));
    }

    @Override
    public void onClick(final View v) {

        Post currentPost = (Post) v.getTag();
        Toast.makeText(this, currentPost.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
