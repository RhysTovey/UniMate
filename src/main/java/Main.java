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
    public static void main(String[] args) throws InterruptedException {
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
            String var;
            switch (var = input.nextLine()) {
                // Calls to create task
                case "1" :
                    System.out.println(displayAddMenu());
                    System.out.println("Enter task name: ");
                    String title = input.nextLine();
                    System.out.println("Enter task description: ");
                    String description = input.nextLine();
                    LocalDate date = LocalDate.now();
                    System.out.println("Task created at: " + date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear());
                    LocalDate deadline = TaskManager.deadlineEntry(input);
                    System.out.println("Will this task repeat? (Yes/No) ");
                    boolean repeats = TaskManager.yesOrNo(input);
                    if (repeats) {

                    }
                    System.out.println("Have you already completed this task? (Yes/No) ");
                    boolean completed = TaskManager.yesOrNo(input);
                    TaskManager.createTask(title, description, date, deadline, completed);
                    break;
                case "2" :
                    // Enables user to remove a task given by Task ID displayed in task list
                    System.out.println(displayRemoveMenu());
                    TaskManager.printActiveTaskList(var);
                    System.out.println("Please enter the task ID you want to remove: ");
                    TaskManager.removeTask(input.nextLine());
                    break;

                case "3" :
                    // Displays all active tasks
                    System.out.println("*** All Active Tasks ***");
                    TaskManager.printActiveTaskList(var);
                    break;

                case "4" :
                    // Enables user to mark task as complete
                    System.out.println("*** Printing active tasks ***");
                    TaskManager.printActiveTaskList(var);
                    System.out.println("Please enter the task ID you want to mark as complete ");
                    TaskManager.markComplete(input.nextLine());
                    break;

                case "5" :
                    System.out.println("*** Printing completed tasks ***");
                    TaskManager.printCompletedTaskList(var);
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
                5: Display Completed Tasks\s
                0: Exit""";
    }

    public static String displayAddMenu(){
        return "*** Add Tasks ***";
    }

    public static String displayRemoveMenu(){
        return "*** Remove Tasks ***";
    }


}
