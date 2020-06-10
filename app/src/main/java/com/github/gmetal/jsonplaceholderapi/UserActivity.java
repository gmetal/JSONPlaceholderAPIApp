package com.github.gmetal.jsonplaceholderapi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    public static final String USER_ID_EXTRA = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toast.makeText(this, "Opened UserActivity with user id: " + getIntent().getIntExtra(USER_ID_EXTRA, 0), Toast.LENGTH_SHORT).show();
    }
}