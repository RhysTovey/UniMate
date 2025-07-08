import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(displayMainMenu());
        Scanner input = new Scanner(System.in);
        // While flag is true
        boolean flag = true;

        while (flag) {
            System.out.print(">> ");
            switch (input.nextLine()) {
                case "1" :
                    System.out.println(displayAddMenu());
                    TaskManager.createTask();
                    break;
                case "2" :
                    break;

                case "3" :
                    break;

                case "4" :
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
        StringBuilder menu = new StringBuilder();
        menu.append(" *** Main Menu *** \n");
        menu.append("1: Add Task " + "\n");
        menu.append("2: Remove Task " + "\n");
        menu.append("3: Display Tasks " + "\n");
        menu.append("4: Mark Task Complete " + "\n");
        menu.append("0: Exit");
        return menu.toString();
    }

    public static String displayAddMenu(){
        StringBuilder menu = new StringBuilder();
        menu.append("*** Add Tasks ***");
        return menu.toString();
    }


}
