package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    // Static list to store tasks
    private static List<Task> tasks = new ArrayList<>();

    // Method to add a task
    public static void addTask(Task task) {
        tasks.add(task);
    }

    // Method to get all tasks
    public static List<Task> getTasks() {
        return tasks;
    }

    // Method to remove a task by index
    public static void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    // Method to mark a task as done
    public static void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
        }
    }
}
