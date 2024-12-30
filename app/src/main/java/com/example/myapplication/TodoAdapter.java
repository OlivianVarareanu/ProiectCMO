package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private final ArrayList<Todo> todoList;

    public TodoAdapter(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo currentTodo = todoList.get(position);
        holder.taskText.setText(currentTodo.getTask()); // Set task title
        holder.taskCheckBox.setChecked(currentTodo.isDone()); // Set task completion status

        // Listener to toggle task completion status
        holder.taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTodo.setDone(isChecked);
            String status = isChecked ? "Completed" : "Pending";
            Toast.makeText(buttonView.getContext(), currentTodo.getTask() + " is " + status, Toast.LENGTH_SHORT).show();
        });

        // Edit Task on click
        holder.itemView.setOnClickListener(v -> {
            // Pass the context from itemView (to show dialog)
            showEditTaskDialog(v.getContext(), currentTodo, position);
        });

        // Delete Task on long click
        holder.itemView.setOnLongClickListener(v -> {
            showDeleteConfirmationDialog(v.getContext(), position);
            return true;
        });

        // Mark task as done when button is clicked
        holder.markDoneButton.setOnClickListener(v -> {
            currentTodo.setDone(true);
            notifyItemChanged(position); // Update the item
        });

        // Delete the task when button is clicked
        holder.deleteButton.setOnClickListener(v -> {
            todoList.remove(position); // Remove the task from the list
            notifyItemRemoved(position); // Notify the adapter of the change
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    // Show dialog to edit task
    private void showEditTaskDialog(Context context, Todo todo, int position) {
        final EditText input = new EditText(context);
        input.setText(todo.getTask()); // Set current task as the default text

        new AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newTask = input.getText().toString().trim();
                    if (!newTask.isEmpty()) {
                        todo.setTask(newTask); // Update task text
                        notifyItemChanged(position); // Notify adapter of task change
                    } else {
                        Toast.makeText(context, "Task cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Show dialog to confirm task deletion
    private void showDeleteConfirmationDialog(Context context, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    todoList.remove(position); // Remove task from list
                    notifyItemRemoved(position); // Notify adapter of removal
                })
                .setNegativeButton("No", null)
                .show();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView taskText; // Task title TextView
        CheckBox taskCheckBox; // CheckBox for marking task as done
        Button markDoneButton; // Button to mark task as done
        Button deleteButton; // Button to delete task

        public TodoViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText); // Bind task text
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox); // Bind checkBox
            markDoneButton = itemView.findViewById(R.id.markDoneButton); // Bind "Mark Done" button
            deleteButton = itemView.findViewById(R.id.deleteButton); // Bind "Delete" button
        }
    }
}
