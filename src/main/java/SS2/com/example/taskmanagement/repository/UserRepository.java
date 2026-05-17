package SS2.com.example.taskmanagement.repository;



import SS2.com.example.taskmanagement.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1L, "Nguyễn Văn An", "nguyenvanan@gmail.com", "ADMIN"));
        users.add(new User(2L, "Lê Thị Bích", "lethibich@gmail.com", "USER"));
        users.add(new User(3L, "Trần Văn Cường", "tranvancuong@gmail.com", "USER"));
    }

    public List<User> findAll() {
        return users;
    }
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}