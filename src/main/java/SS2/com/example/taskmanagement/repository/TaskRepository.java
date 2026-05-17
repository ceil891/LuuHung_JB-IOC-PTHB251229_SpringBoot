package SS2.com.example.taskmanagement.repository;

import SS2.com.example.taskmanagement.model.Task;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        tasks.add(new Task(1L, "Task 1", "Mô tả 1", "high", 1L));
        tasks.add(new Task(2L, "Task 2", "Mô tả 2", "medium", 1L));
        tasks.add(new Task(3L, "Task 3", "Mô tả 3", "low", 2L));
        tasks.add(new Task(4L, "Task 4", "Mô tả 4", "high", 2L));
        tasks.add(new Task(5L, "Task 5", "Mô tả 5", "medium", 3L));
        tasks.add(new Task(6L, "Task 6", "Mô tả 6", "low", 3L));
        tasks.add(new Task(7L, "Task 7", "Mô tả 7", "high", 1L));
        tasks.add(new Task(8L, "Task 8", "Mô tả 8", "medium", 2L));
        tasks.add(new Task(9L, "Task 9", "Mô tả 9", "low", 1L));
        tasks.add(new Task(10L, "Task 10", "Mô tả 10", "high", 3L));
    }

    public List<Task> findAll() {
        return tasks;
    }
    public Task save(Task task) {
        this.tasks.add(task);
        return task;
    }

}