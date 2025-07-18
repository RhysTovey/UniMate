import journaling.Journal;
import journaling.JournalCLI;
import notesystem.NoteCLI;
import notesystem.NoteManager;
import taskmanagersystem.Task;
import taskmanagersystem.TaskCLI;
import taskmanagersystem.TaskManager;
import taskmanagersystem.TaskService;

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
        NoteManager noteManager = new NoteManager();
        NoteCLI noteCLI = new NoteCLI(noteManager);
        TaskManager taskManager = new TaskManager();
        TaskService taskService = new TaskService(taskManager);
        TaskCLI taskCLI = new TaskCLI(taskService, taskManager);
        Journal journal = new Journal();
        JournalCLI journalCLI = new JournalCLI(journal);
        while (running) {
            System.out.println(displayMainMenu());
            System.out.print(">> ");
            switch (input.nextLine()) {
                case "1":
                    taskCLI.run();
                    break;
                case "2":
                    journalCLI.run();
                    break;
                case "3":
                    noteCLI.run();
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

    public static String displayAddMenu() {
        return "*** Add Tasks ***";
    }

    public static String displayRemoveMenu() {
        return "*** Remove Tasks ***" + "\n" +
                "1. Active Tasks " + "\n" +
                "2. Completed Tasks" + "\n" +
                "3. Recurring Tasks" + "\n" +
                "BACK: to return to main menu";
    }


    public static void returnToMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Type 'back' to return to main menu" + "\n" + ">> ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("back")) {
            System.out.println("Back to main menu");
        } else {
            System.out.println("Invalid input. Try again.");
            returnToMenu();
        }
    }
}

