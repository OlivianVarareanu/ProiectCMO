package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button viewTasksButton, createTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Main screen layout

        // Initialize buttons
        viewTasksButton = findViewById(R.id.viewTasksButton);
        createTaskButton = findViewById(R.id.createTaskButton);

        // Set up View Tasks button click
        viewTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewTaskActivity.class);
            startActivity(intent);
        });

        // Set up Create Task button click
        createTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
            startActivity(intent);
        });
    }
}
