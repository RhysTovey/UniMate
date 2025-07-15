import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * Main Class - Provides CLI User interaction
 *
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        runCLI();


    }

    public static void runCLI() throws InterruptedException {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to UniMate");
//        Thread.sleep(2000);
        while (running) {
            System.out.println(displayMainMenu());
            System.out.print(">> ");
            switch (input.nextLine()) {
                case "1":
                    runTaskManager();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "0":
                    System.out.println("Exiting...");
                    running = false;
                    break;


                default:
                    System.out.println("Invalid input. Try again.");



            }
        }

    }



    public static String displayMainMenu() {
        return """
                 ***** Main Menu *****\s
                1: Task Manager \s
                2: Journaling \s
                3: Note Making \s
                4: Add Modules \s
                5: Calendar \s
                6: Study Sessions \s
                7: Emails \s
                0: Exit""";
    }

    public static String displayTaskManager() {
        return """
                 *** Task Manager System ***\s
                1: Add Task\s
                2: Remove Task\s
                3: Mark Task Complete\s
                4: View Active Tasks\s
                5: View Completed Tasks\s
                6: View Recurring Tasks\s
                0: Return to Main Menu""";
    }

    public static String displayAddMenu(){
        return "*** Add Tasks ***";
    }

    public static String displayRemoveMenu(){
        return "*** Remove Tasks ***" + "\n" +
                "1. Active Tasks " + "\n" +
                "2. Completed Tasks"  + "\n" +
                "3. Recurring Tasks" + "\n" +
                "BACK: to return to main menu";
    }

    public static String displayRecurrenceOptions() {
        return "1. Daily " + "\n" +
                "2. Weekly " + "\n" +
                "3. Monthly " + "\n" +
                 "4. Yearly ";
    }

    public static void returnToMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Type 'back' to return to main menu" + "\n" + ">> ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("back")) {
            System.out.println("Back to main menu");
        }
        else {
            System.out.println("Invalid input. Try again.");
            returnToMenu();
        }
    }

    public static void runTaskManager() throws InterruptedException {
        TaskManager.readTasks();
        TaskManager.readCompletedTasks();
        TaskManager.readRecurringTasks();
//        TaskManager.checkAndGenerateRecurringTask();
        Scanner input = new Scanner(System.in);
        // While flag is true
        boolean flag = true;
        // Boolean enabled menu system
        // Switch statements to control menu selection
        while (flag) {
            // Loops to display main menu
            System.out.println(displayTaskManager());
            System.out.print(">> ");
            String choice = input.nextLine();
            switch (choice) {
                // Calls to create task
                case "1" :
                    // Prompt user for Input to fill in each param to create a new task
                    System.out.println(displayAddMenu());
                    TaskManager.createTaskInput();


                    break;
                case "2" :
                    // Enables user to remove a task given by Task ID displayed in task list
                    System.out.println(displayRemoveMenu());
                    System.out.println("Please select which type of task you would like to remove: (1/2/3)");
                    System.out.print(">> ");
                    String removeOption = input.nextLine();
                    TaskManager.removeTask(removeOption);
                    break;

                case "4" :
                    // Displays all active tasks
                    System.out.println("*** All Active Tasks ***");
                    TaskManager.printActiveTaskList();
                    returnToMenu();
                    break;

                case "3" :
                    // Enables user to mark task as complete
                    System.out.println("*** Printing active tasks ***");
                    TaskManager.printActiveTaskList();
                    //Choice to return to main menu
                    TaskManager.markComplete();
                    break;


                case "5" :
                    System.out.println("*** Printing completed tasks ***");
                    TaskManager.printCompletedTaskList(choice);
                    returnToMenu();
                    break;


                case "6" :
                    System.out.println("*** Printing Recurring tasks ***");
                    TaskManager.printRecurringTaskList();
                    returnToMenu();
                    break;

                case "0" :
                    // Exits program
                    System.out.println("Returning to main menu...");
                    flag = false;
                    break;

                default:
                    break;
            }

        }
    }
}
