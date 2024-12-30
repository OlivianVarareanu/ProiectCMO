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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo currentTodo = todoList.get(position);
        holder.taskText.setText(currentTodo.getTask());
        holder.taskCheckBox.setChecked(currentTodo.isDone());

        holder.taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTodo.setDone(isChecked);
            String status = isChecked ? "Completed" : "Pending";
            Toast.makeText(buttonView.getContext(), currentTodo.getTask() + " is " + status, Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {

            showEditTaskDialog(v.getContext(), currentTodo, position);
        });

        holder.itemView.setOnLongClickListener(v -> {
            showDeleteConfirmationDialog(v.getContext(), position);
            return true;
        });

        holder.markDoneButton.setOnClickListener(v -> {
            currentTodo.setDone(true);
            notifyItemChanged(position);
        });

        holder.deleteButton.setOnClickListener(v -> {
            todoList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    // Show dialog to edit task
    private void showEditTaskDialog(Context context, Todo todo, int position) {
        final EditText input = new EditText(context);
        input.setText(todo.getTask());

        new AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newTask = input.getText().toString().trim();
                    if (!newTask.isEmpty()) {
                        todo.setTask(newTask);
                        notifyItemChanged(position);
                    } else {
                        Toast.makeText(context, "Task cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteConfirmationDialog(Context context, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    todoList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("No", null)
                .show();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox taskCheckBox;
        Button markDoneButton;
        Button deleteButton;

        public TodoViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox);
            markDoneButton = itemView.findViewById(R.id.markDoneButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
