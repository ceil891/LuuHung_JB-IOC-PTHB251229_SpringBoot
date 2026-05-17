package SS2.com.example.taskmanagement.controller;

import SS2.com.example.taskmanagement.model.Task;
import SS2.com.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "search", required = false) String search) {

        List<Task> allTasks = taskService.findAllTasks();


        if (search != null && !search.trim().isEmpty()) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }


        return ResponseEntity.ok(allTasks);
    }
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        Task createdTask = taskService.createTask(newTask);

        if (createdTask != null) {
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Lỗi: Người dùng được giao việc (assignedTo) không tồn tại trong hệ thống!");
        }
    }
}