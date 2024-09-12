import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToDoListApp {
    private final Map<String, User> users; // Map of registered users by their ID
    private User loggedInUser; // Current logged-in user
    private final Scanner scanner;

    public ToDoListApp() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please try another one.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.put(newUser.getId(), newUser); // Use UUID as the key
        System.out.println("Registration successful! Your User ID is: " + newUser.getId());
    }

    private boolean isUsernameTaken(String username) {
        return users.values().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful! Welcome, " + user.getUsername());
            manageTasks(); // Show task management menu
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private User findUserByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void manageTasks() {
        while (true) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Logout");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task(description);
        loggedInUser.addTask(task);
        System.out.println("Task added successfully. Task ID: " + task.getId());
    }

    public void viewTasks() {
        System.out.println("Your tasks:");
        for (Task task : loggedInUser.getTasks()) {
            System.out.println(task);
        }
    }

    public void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        String taskId = scanner.nextLine();

        Task task = loggedInUser.getTaskById(taskId);

        if (task != null) {
            loggedInUser.deleteTask(task);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome to the To-Do List App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.start();
    }
}