package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText taskTitle, taskDescription;
    private Button saveTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task); // Layout for creating task

        // Initialize views
        taskTitle = findViewById(R.id.taskTitle);
        taskDescription = findViewById(R.id.taskDescription);
        saveTaskButton = findViewById(R.id.saveTaskButton);

        // Set save task button click listener
        saveTaskButton.setOnClickListener(v -> {
            String title = taskTitle.getText().toString().trim();
            String description = taskDescription.getText().toString().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                // Save the task
                Task newTask = new Task(title, description);
                TaskManager.addTask(newTask);  // This is correct if TaskManager is managing tasks

                Toast.makeText(CreateTaskActivity.this, "Task Saved!", Toast.LENGTH_SHORT).show();
                finish();  // Close activity and return to the main menu
            } else {
                Toast.makeText(CreateTaskActivity.this, "Please enter both title and description.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
