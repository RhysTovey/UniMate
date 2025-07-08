import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static List<Task> activeTaskList = new ArrayList<>();
    private static List<Task> completedTaskList = new ArrayList<>();

    public static List<Task> getActiveTaskList() {
        return activeTaskList;
    }
    public static List<Task> getCompletedTaskList() {
        return completedTaskList;
    }

    public static void addTask(Task task) {
        activeTaskList.add(task);
    }
    public static void removeTask(Task task) {
        activeTaskList.remove(task);
    }

    public static void createTask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter task name: ");
        String title = input.nextLine();
        System.out.println("Enter task description: ");
        String description = input.nextLine();
        System.out.println("Task created at: " + LocalDateTime.now());
        LocalDate date = LocalDate.now();
        System.out.println("Enter deadline year: ");
        int year = input.nextInt();
        System.out.println("Enter deadline month: ");
        int month = input.nextInt();
        System.out.println("Enter deadline day: ");
        int day = input.nextInt();
        LocalDate deadline = LocalDate.of(year, month, day);
        System.out.println("Will this task repeat (true/false) ");
        boolean repeats = input.nextBoolean();
        System.out.println("Have you already completed this task? (true/false) ");
        boolean completed = input.nextBoolean();
        Task newTask = new Task(title, description, date, deadline, repeats, completed);
        System.out.println("New task created: " + newTask.toString());
        activeTaskList.add(newTask);
    }

}
