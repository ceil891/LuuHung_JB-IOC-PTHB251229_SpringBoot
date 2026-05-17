package SS2.com.example.taskmanagement.service;


import SS2.com.example.taskmanagement.model.Task;
import SS2.com.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor Injection
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    public Task createTask(Task newTask) {
        // Kiểm tra xem assignedTo có truyền lên không và có tồn tại không
        if (newTask.getAssignedTo() == null) {
            return null;
        }

        boolean isUserExist = userService.findUserById(newTask.getAssignedTo()).isPresent();

        if (isUserExist) {
            return taskRepository.save(newTask); // Thỏa mãn điều kiện -> Lưu
        }

        return null; // Không tìm thấy User -> Trả về null để báo lỗi ở Controller
    }


}