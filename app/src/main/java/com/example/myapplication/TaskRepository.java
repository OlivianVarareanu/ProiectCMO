package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> taskList = new ArrayList<>();

    public List<Task> getTasks() { return taskList; }
    public void addTask(Task task) { taskList.add(task); }
    public void removeTask(int index) { taskList.remove(index); }
}
