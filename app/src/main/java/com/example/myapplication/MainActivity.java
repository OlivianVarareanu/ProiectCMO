package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button viewTasksButton, createTaskButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewTasksButton = findViewById(R.id.viewTasksButton);
        createTaskButton = findViewById(R.id.createTaskButton);
        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        viewTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewTaskActivity.class);
            startActivity(intent);
        });

        createTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
            startActivity(intent);
        });
    }
}
