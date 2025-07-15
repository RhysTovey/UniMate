import java.io.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskManager {
    private static List<Task> activeTaskList = new ArrayList<>();
    private static List<Task> completedTaskList = new ArrayList<>();
    private static List<RecurringTask> recurringTaskList = new ArrayList<>();

    /**
     *
     * @param title
     * @param description
     * @param creationDate
     * @param deadline
     * @param completed
     *
     * Takes params from CLI to create a new task, if complete is true adds to completed task list
     *
     */


    public static void createTask(String title, String description, LocalDate creationDate, LocalDate deadline, boolean completed) {
        try {
            Task newTask = new Task(title, description, creationDate, deadline, completed);
            System.out.println("New task created: " + "\n" + newTask);
            System.out.println("Returning to main menu");
            Thread.sleep(1000);
            if (completed) {
                completedTaskList.add(newTask);
            }
            else  {
                activeTaskList.add(newTask);
            }
            saveTasks();
        }

        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void createRecurringTask(String title, String description ,LocalDate creationDate, LocalDate deadline, boolean completed,
                                           RecurrenceType recurrenceType, LocalDate endDate) {
        try {
            RecurringTask newRecurringTask =
                    new RecurringTask(title, description, creationDate, deadline, completed, recurrenceType, endDate);

            System.out.println("New recurring task created: " + "\n" + newRecurringTask);
            System.out.println("Returning to main menu");
            recurringTaskList.add(newRecurringTask);
            Thread.sleep(1500);
            saveTasks();
        }
        catch (Exception e) {
            System.out.println("Task failed to create: " + e.getMessage());
        }
    }

    /**
     * printTaskList
     * Neatly prints task list for user, showing title, description deadline and ID
     */

    public static void printActiveTaskList() throws InterruptedException {
        if  (activeTaskList.isEmpty()) {
            System.out.println("No active tasks found!");
        }
        if (!activeTaskList.isEmpty()) {
            for (Task task : activeTaskList) {
                Thread.sleep(1000);
                System.out.println("<---- Task ID " + activeTaskList.indexOf(task) +  " ---->");
                System.out.println("Title: " + task.getTitle() + "\n"
                        + "Description: "+task.getDescription() + "\n" +
                        "Deadline: "+task.getDeadline().toString() + "\n");
            }
        }
    }

    /**
     * @param input
     * Neatly prints task list for user, showing title, description deadline and ID
     */

    public static void printCompletedTaskList(String input) {
        if (completedTaskList.isEmpty()) {
            System.out.println("No completed tasks found!");
        }

        if (input.equals("5")) {
            for (Task task : completedTaskList) {
                System.out.println("<---- Task ID " + completedTaskList.indexOf(task) +  " ---->");
                System.out.println("Title: " + task.getTitle() + "\n"
                        + "Description: "+task.getDescription() + "\n" +
                        "Deadline: "+task.getDeadline().toString() + "\n");
            }
        }
    }

    /**
     * printRecurringTaskList
     */

    public static void printRecurringTaskList() {
        if (recurringTaskList.isEmpty()) {
            System.out.println("No recurring tasks found!");
        }
        else {
            for (RecurringTask task : recurringTaskList) {
                System.out.println("<---- Task ID " + recurringTaskList.indexOf(task) +  " ---->");
                System.out.println("Title: " + task.getTitle() + "\n"
                        + "Description: "+task.getDescription() + "\n"
                + "Deadline: "+task.getDeadline().toString() + "\n"
                + "Re-occurs: " + task.getRecurrenceType().toString() + "\n" +
                        "Next occurrence date: " + task.getNextOccurenceDate().toString() + "\n");
            }
        }


    }

    /**
     * checkAndGenerateRecurringTask
     */

    public static void checkAndGenerateRecurringTask() {
        LocalDate today  = LocalDate.now();

        for (RecurringTask task : recurringTaskList) {
            if (!task.getDeadline().isAfter(ChronoLocalDate.from(today.atTime(23,59)))) {
                Task newInstance = new Task(
                        task.getTitle(),
                        task.getDescription(),
                        task.getDate(),
                        task.setDeadline(task.getNextOccurenceDate()),
                        false
                );
                activeTaskList.add(task);
                System.out.println("New Task generated");
                task.updateNextOccurenceDate();
            }
        }
        saveTasks();
    }



    /**
     * removeTask
     * Checks if Tasks exist, if so, tasks are displayed and the user can type the ID and delete
     */
    public static void removeTask(String id) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        switch (id) {
            case "1":
                if (!activeTaskList.isEmpty()) {
                    for (Task task : activeTaskList) {
                        System.out.println("<---- Task ID " + activeTaskList.indexOf(task) +  " ---->");
                        System.out.println(task.toString());
                        break;
                    }
                    System.out.println("Please enter the task ID you would like to remove: ");
                    System.out.print(">> ");
                    try {
                        int index = Integer.parseInt(input.nextLine());
                        activeTaskList.remove(index);
                        System.out.println("Task successfully removed!");
                        saveTasks();
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task ID");
                        removeTask(id);
                    }

                }
                break;

            case "2":
                if (!completedTaskList.isEmpty()) {
                    for (Task task : completedTaskList) {
                        System.out.println("<---- Task ID " + completedTaskList.indexOf(task) +  " ---->");
                        System.out.println(task.toString());
                    }
                    System.out.println("Please enter the task ID you would like to remove: ");
                    System.out.print(">> ");
                    try {
                        int index = Integer.parseInt(input.nextLine());
                        completedTaskList.remove(index);
                        System.out.println("Task successfully removed!");
                        saveTasks();
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task ID");
                        removeTask(id);
                    }
                }
                break;

            case "3":
                if  (!recurringTaskList.isEmpty()) {
                    for (RecurringTask task : recurringTaskList) {
                        System.out.println("<---- Task ID " + recurringTaskList.indexOf(task) +  " ---->");
                        System.out.println(task.toString());
                    }
                    System.out.println("Please enter the task ID you would like to remove: ");
                    System.out.print(">> ");
                    try {
                        int index = Integer.parseInt(input.nextLine());
                        recurringTaskList.remove(index);
                        System.out.println("Task successfully removed!");
                        saveTasks();
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task ID");
                        removeTask(id);
                    }
                }
                break;

            case "back" :
                System.out.println("Returning to main menu");
                break;

            default:
                System.out.println("Invalid Option, returning to menu");
                break;
        }
    }

    /**
     * markComplete
     * Takes an ID from user to mark this task as complete, sets complete to true and adds to completedTaskList
     */

    public static void markComplete() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the ID of the task you would like to complete: ");
        System.out.print(">> ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("back")) {
            System.out.println("Returning to main menu");
        }
        else {
            try {
                int id = Integer.parseInt(answer);
                try {
                    Task task = activeTaskList.get(id);
                    task.setComplete(true);
                    activeTaskList.remove(id);
                    System.out.println(task + "Task marked as complete!");
                    completedTaskList.add(task);
                    saveTasks();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            "/// Error ///" + "\n"
                            + "Please enter a valid task ID - Try Again");
                    markComplete();
                }
            }
            catch (NumberFormatException e) {
                System.out.println("/// Error ///");
                System.out.println("Input isn't recognised");
                markComplete();
            }
        }

    }

    /**
     *
     * @param input
     * @return
     *
     * Encapsulated deadLineEntry logic intro a method for better readability
     * Called when user is creating a task
     */

    public static LocalDate dateEntry(Scanner input) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print(">> ");
                String dl = input.nextLine();
                date = LocalDate.parse(dl);
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again");
            }
        }
        return date;

    }

    /**
     * @param input
     * @return
     *
     * Encapsulated logic for multiple re-use
     */

    public static boolean yesOrNo (Scanner input) {
        while (true) {
            // Take scanner input and return bool dependent on input
            System.out.print(">> ");
            String result = input.nextLine();
            if (result.equalsIgnoreCase("yes")) {return true;}
            if (result.equalsIgnoreCase("no")) {return false;}
            System.out.println("Invalid input - Please try again");
        }

    }

    /**
     * Saves tasks via ObjectOutputStream to activetasks.dat
     * Uses serialization to maintain objects within activeTaskList arraylist
     *
     */

    public static void saveTasks() {
        try {
            // Establish Output streams for both files, open -> write -> close
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/activetasks.dat"));
            oos.writeObject(activeTaskList);
            oos.close();

            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("src/main/resources/completedtasks.dat"));
            oos2.writeObject(completedTaskList);
            oos2.close();

            ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("src/main/resources/recurringtasks.dat"));
            oos3.writeObject(recurringTaskList);
            oos3.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * readTasks()
     * Reads back in Serialized tasks using ObjectInputStream, populates activeTaskList with cast of inputStream objects
     *
     * @throws ClassCastException
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */

    public static void readTasks() {
        while (true) {
            // Try to establish input stream and readObjects within - Break and continue if found
            try {
                ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream("src/main/resources/activetasks.dat"));

                activeTaskList = (ArrayList<Task>) input.readObject();
                input.close();
                break;
            }
            // Catches EOF exception which arises if file is empty, breaks and continues in program
            catch (EOFException e) {
                System.out.println("EOF Exception - No data to load");
                break;
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void readCompletedTasks() {
        while (true) {
            // Try to establish input stream and readObjects within - Break and continue if found
            try {
                ObjectInputStream fileInput = new ObjectInputStream(
                        new FileInputStream("src/main/resources/completedtasks.dat"));

                completedTaskList = (ArrayList<Task>) fileInput.readObject();
                fileInput.close();
                break;
            }
            // Catches EOF exception which arises if file is empty, breaks and continues in program
            catch (EOFException e) {
                System.out.println("EOF Exception - No data to load");
                break;
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void readRecurringTasks() {
        while (true) {
            // Try to establish input stream and readObjects within - Break and continue if found
            try {
                ObjectInputStream fileInput = new ObjectInputStream(
                        new FileInputStream("src/main/resources/recurringtasks.dat"));

                recurringTaskList = (List<RecurringTask>) fileInput.readObject();
                fileInput.close();
                break;
            }
            // Catches EOF exception which arises if file is empty, breaks and continues in program
            catch (EOFException e) {
                System.out.println("EOF Exception - No data to load");
                break;
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }

        }
    }

}
