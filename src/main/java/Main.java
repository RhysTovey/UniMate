import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskManager.readTasks();
        Scanner input = new Scanner(System.in);
        // While flag is true
        boolean flag = true;

        // Boolean enabled menu system
        // Switch statements to control menu selection
        while (flag) {
            System.out.println(displayMainMenu());
            System.out.print(">> ");
            switch (input.nextLine()) {
                case "1" :
                    System.out.println(displayAddMenu());
                    TaskManager.createTask();
                    break;
                case "2" :
                    System.out.println(displayRemoveMenu());
                    TaskManager.printTaskList();
                    System.out.println("Please enter the task ID you want to remove: ");
                    TaskManager.removeTask(input.nextLine());
                    break;

                case "3" :
                    System.out.println("*** All Active Tasks ***");
                    TaskManager.printTaskList();
                    break;

                case "4" :
                    System.out.println("*** Printing active tasks ***");
                    TaskManager.printTaskList();
                    System.out.println("Please enter the task ID you want to mark as complete ");
                    TaskManager.markComplete(input.nextLine());

                    break;

                case "0" :
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
