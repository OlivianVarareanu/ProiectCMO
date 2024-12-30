package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<>();
    public static void addTask(Task task) {
        tasks.add(task);
    }
    public static List<Task> getTasks() {
        return tasks;
    }

    public static void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public static void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
        }
    }
}
