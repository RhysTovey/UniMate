package taskmanagersystem;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService;
    private final TaskManager taskManager;

    public TaskCLI(TaskService service, TaskManager taskManager) {
        this.taskService = service;
        this.taskManager = taskManager;
    }

    public void run() {
        while (true) {
            System.out.println("""
                *** Task Manager System ***\s
                1: Add Task\s
                2: Add Recurring Task\s
                3: Remove Task\s
                4: Mark Task Complete\s
                5: View Active Tasks\s
                6: View Completed Tasks\s
                7: View Recurring Tasks\s
                0: Return to Main Menu""");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createTaskFlow();
                    break;
                case 2:
                    createRecurringTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    markComplete();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    System.out.println("Returning to Main Menu");
                    break;

                default:
                    System.out.println("Invalid choice");
           }
        }
    }

    private void createTaskFlow() {
        scanner.nextLine();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter description: ");
        String desc = scanner.nextLine();
        LocalDate deadline = dateHelper();
        taskService.createTask(title, desc, LocalDate.now(), deadline, false);
        System.out.println("Task created!");
    }

    private void createRecurringTask() {
        scanner.nextLine();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter description: ");
        String desc = scanner.nextLine();
        System.out.println("Enter deadline (YYYY-MM-DD): ");
        LocalDate deadline = dateHelper();
        System.out.println("""
                How often would you like this task to repeat?\s
                > Daily\s
                > Weekly\s
                > Monthly\s
                > Yearly\s""");
        RecurrenceType type;
        while (true) {
            try {
                type = RecurrenceType.valueOf(scanner.nextLine().toUpperCase().trim());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        System.out.println("When would you like this to repeat until? (YYYY-MM-DD)");
        LocalDate endDate = dateHelper();
        taskService.createRecurringTask(title, desc, LocalDate.now(), deadline, false, type, endDate);
        System.out.println("Task created!");
    }

    private void removeTask() {
        printRmvOptions();
        while (true) {
            switch (scanner.nextLine()) {
                case "1" -> removeHelper(taskManager.getActiveTasks(), taskManager);
                case "2" ->  removeHelper(taskManager.getCompletedTasks(), taskManager);
                case "3" ->  removeHelper(taskManager.getRecurringTasks(), taskManager);
                case "back", "exit" ->
                    System.out.println("Returning to menu");
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void printRmvOptions() {
        System.out.println("""
                1. Active Tasks
                2. Completed Tasks
                3. Recurring Tasks
                """);
    }

    private void markComplete() {
        System.out.println("Display Active Tasks");
        int id;
        for (int i =  0; i < taskManager.getActiveTasks().size(); i++) {
            System.out.println("<----- Task ID " + i + " ----->");
            System.out.println(taskManager.getActiveTasks().get(i));
        }
        System.out.println("Please enter the ID of the task you'd like to mark as complete ");
        System.out.println(">> ");
        try {
            id = scanner.nextInt();
            if(id >= 0 && id < taskManager.getActiveTasks().size()) {
                taskManager.markTaskComplete(id);
                System.out.println("Task marked as completed!");
            }
            else {
                System.out.println("Invalid ID");
                return;
            }
        }
        catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid ID");
            return;
        }
    }

    private void removeHelper(List<? extends Task> tasks, TaskManager taskManager) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("Task ID: " + i);
            System.out.println(tasks.get(i).toString());
            System.out.println("___________");
        }
        int id;
        try {
            System.out.println("Enter task ID to be removed: ");
            id = Integer.parseInt(scanner.nextLine());
        }
        catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            System.out.println("Invalid task ID");
            return;
        }
        if (id >= 0 && id < tasks.size()){
            tasks.remove(id);
            System.out.println("Task ID: " + id);
        }
        else {
            System.out.println("Invalid task ID");
        }
    }

    private LocalDate dateHelper(){
        LocalDate date = null;
        while (true) {
            try {
                date = LocalDate.parse(scanner.nextLine());
                break;
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid date format");
            }
        }
        return date;
    }










}
