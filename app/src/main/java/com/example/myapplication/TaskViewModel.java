package com.example.myapplication;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class TaskViewModel extends ViewModel {
    private final TaskRepository repository = new TaskRepository();
    private final MutableLiveData<List<Task>> tasks = new MutableLiveData<>();

    public TaskViewModel() {
        tasks.setValue(repository.getTasks());
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        repository.addTask(task);
        tasks.setValue(repository.getTasks());
    }

    public void removeTask(int index) {
        repository.removeTask(index);
        tasks.setValue(repository.getTasks());
    }
}
