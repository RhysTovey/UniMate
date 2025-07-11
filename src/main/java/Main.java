import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * Main Class - Provides CLI User interaction
 *
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskManager.readTasks();
        TaskManager.readCompletedTasks();
        Scanner input = new Scanner(System.in);
        // While flag is true
        boolean flag = true;

        // Boolean enabled menu system
        // Switch statements to control menu selection
        while (flag) {
            // Loops to display mainmenu
            System.out.println(displayMainMenu());
            System.out.print(">> ");
            String choice = input.nextLine();
            String answer;
            switch (choice) {
                // Calls to create task
                case "1" :
                    // Prompt user for Input to fill in each param to create a new task
                    System.out.println(displayAddMenu());
                    System.out.print("Enter task name: " + "\n" + ">> ");
                    String title = input.nextLine();
                    System.out.print("Enter task description: " + "\n" + ">> ");
                    String description = input.nextLine();
                    LocalDate startDate = LocalDate.now();
                    System.out.println("Task created at: "
                            + startDate.getDayOfMonth() + "-" + startDate.getMonthValue() + "-" + startDate.getYear());
                    LocalDate deadline = TaskManager.deadlineEntry(input);
                    System.out.print("Will this task repeat? (Yes/No)");
                    boolean repeats = TaskManager.yesOrNo(input);
                    System.out.print("Have you already completed this task? (Yes/No) ");
                    boolean completed = TaskManager.yesOrNo(input);
                    TaskManager.createTask(title, description, startDate, deadline, completed);
                    break;
                case "2" :
                    // Enables user to remove a task given by Task ID displayed in task list
                    System.out.println(displayRemoveMenu());
                    TaskManager.printActiveTaskList();
                    System.out.println("Please enter the task ID you want to remove: ");
                    System.out.println("Or type 'back' to return to main menu");
                    System.out.print(">> ");
                    answer = input.nextLine();
                    try {
                        if (answer.equalsIgnoreCase("back")) {
                            break;
                        }
                        TaskManager.removeTask(answer);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input, redirecting to main menu");
                        Thread.sleep(1500);
                        break;
                    }

                    break;

                case "3" :
                    // Displays all active tasks
                    System.out.println("*** All Active Tasks ***");
                    TaskManager.printActiveTaskList();
                    System.out.println("Press 0 to return to main menu");
                    System.out.print(">> ");
                    answer = input.nextLine();
                    try {
                        if (Objects.equals(answer, "0")){
                            break;
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case "4" :
                    // Enables user to mark task as complete
                    System.out.println("*** Printing active tasks ***");
                    TaskManager.printActiveTaskList();
                    System.out.println("Please enter the task ID you want to mark as complete ");
                    System.out.println("Or type 'back' to return to main menu");
                    System.out.print(">> ");
                    answer = input.nextLine();
                    //Choice to return to main menu

                    try {
                        if (answer.equalsIgnoreCase("back")) {
                            break;
                        }
                        TaskManager.markComplete(answer);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input, redirecting to main menu");
                        Thread.sleep(1500);
                    }
                    break;


                case "5" :
                    System.out.println("*** Printing completed tasks ***");
                    TaskManager.printCompletedTaskList(choice);
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
