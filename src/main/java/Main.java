import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * Main Class - Provides CLI User interaction
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        TaskManager.readTasks();
        Scanner input = new Scanner(System.in);
        // While flag is true
        boolean flag = true;

        // Boolean enabled menu system
        // Switch statements to control menu selection
        while (flag) {
            // Loops to display mainmenu
            System.out.println(displayMainMenu());
            System.out.print(">> ");
            switch (input.nextLine()) {
                // Calls to create task
                case "1" :
                    System.out.println(displayAddMenu());
                    System.out.println("Enter task name: ");
                    String title = input.nextLine();
                    System.out.println("Enter task description: ");
                    String description = input.nextLine();
                    System.out.println("Task created at: " + LocalDateTime.now());
                    LocalDate date = LocalDate.now();
                    LocalDate deadline = TaskManager.deadlineEntry(input);
                    System.out.println("Will this task repeat? ");
                    boolean repeats = TaskManager.yesOrNo(input);
                    System.out.println("Have you already completed this task? ");
                    boolean completed = TaskManager.yesOrNo(input);
                    TaskManager.createTask(title,  description, date, deadline, repeats, completed);
                    break;
                case "2" :
                    // Enables user to remove a task given by Task ID displayed in task list
                    System.out.println(displayRemoveMenu());
                    TaskManager.printTaskList();
                    System.out.println("Please enter the task ID you want to remove: ");
                    TaskManager.removeTask(input.nextLine());
                    break;

                case "3" :
                    // Displays all active tasks
                    System.out.println("*** All Active Tasks ***");
                    TaskManager.printTaskList();
                    break;

                case "4" :
                    // Enables user to mark task as complete
                    System.out.println("*** Printing active tasks ***");
                    TaskManager.printTaskList();
                    System.out.println("Please enter the task ID you want to mark as complete ");
                    TaskManager.markComplete(input.nextLine());
                    break;

                case "0" :
                    // Exits program
                    flag = false;
                    break;

                default:
                    break;
            }

        }
    }

    public static String displayMainMenu() {
        return """
                 *** Main Menu ***\s
                1: Add Task\s
                2: Remove Task\s
                3: Display Tasks\s
                4: Mark Task Complete\s
                0: Exit""";
    }

    public static String displayAddMenu(){
        return "*** Add Tasks ***";
    }

    public static String displayRemoveMenu(){
        return "*** Remove Tasks ***";
    }


}
