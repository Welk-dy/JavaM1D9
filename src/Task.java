import java.util.UUID;

public class Task {
    private final String id;
    private final String description;

    public Task(String description) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the task
        this.description = description;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Description: " + description;
    }
}