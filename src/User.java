import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id; // Unique identifier for the user
    private final String username;
    private final String password;
    private final List<Task> tasks;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for each user
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public Task getTaskById(String taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }
}