import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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




                    break;

                case "3" :
                    System.out.println("*** All Active Tasks ***");
                    System.out.println(TaskManager.getActiveTaskList());
                    break;

                case "4" :
                    System.out.println("*** All Completed Tasks ***");
                    System.out.println(TaskManager.getCompletedTaskList());
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
        return " *** Main Menu *** \n" +
                "1: Add Task " + "\n" +
                "2: Remove Task " + "\n" +
                "3: Display Tasks " + "\n" +
                "4: Mark Task Complete " + "\n" +
                "0: Exit";
    }

    public static String displayAddMenu(){
        return "*** Add Tasks ***";
    }

    public static String displayRemoveMenu(){
        return "*** Remove Tasks ***";
    }


}
