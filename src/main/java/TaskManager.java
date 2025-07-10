import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskManager {
    private static List<Task> activeTaskList = new ArrayList<>();
    private static List<Task> completedTaskList = new ArrayList<>();
//
//    public static List<Task> getActiveTaskList() {
//        return activeTaskList;
//
//    }
//    public static List<Task> getCompletedTaskList() {
//        return completedTaskList;
//    }

    /**
     *
     * @param title
     * @param description
     * @param date
     * @param deadline
     * @param completed
     *
     * Takes params from CLI to create a new task, if complete is true adds to completed task list
     *
     */


    public static void createTask(String title, String description, LocalDate date, LocalDate deadline, boolean completed) {
        try {
            Task newTask = new Task(title, description, date, deadline, completed);
            System.out.println("New task created: " + "\n" + newTask);
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

    /**
     * printTaskList
     * @param input
     * Neatly prints task list for user, showing title, description deadline and ID
     */

    public static void printActiveTaskList(String input) {
        if  (activeTaskList.isEmpty()) {
            System.out.println("No active tasks found!");
        }
        if (input.equals("2") || input.equals("3") || input.equals("4")) {
            for (Task task : activeTaskList) {
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
     * removeTask
     * Checks if Tasks exist, if so, tasks are displayed and the user can type the ID and delete
     */
    public static void removeTask(String id) {
        // Take string ID and parse to Int
        int indexID = Integer.parseInt(id);
        if (!activeTaskList.isEmpty()) {
            // Removes from corresponding index in array
            activeTaskList.remove(indexID);
            System.out.println("Task " + indexID + " has been removed");
            saveTasks();
        }
        else {
            System.out.println("Task does not exist! ");
        }
    }

    /**
     * markComplete
     * Takes an ID from user to mark this task as complete, sets complete to true and adds to completedTaskList
     */

    public static void markComplete(String id) {
        int indexID = Integer.parseInt(id);
        if (!activeTaskList.isEmpty()) {
            // Retrieve task from arrayList by specified ID
            Task task = activeTaskList.get(indexID);
            task.setComplete(true);
            // Add to complete, remove from active
            completedTaskList.add(task);
            activeTaskList.remove(indexID);
            System.out.println("Task " + indexID + " has been marked completed");
        }
        else {
            System.out.println("Task does not exist! ");
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

    public static LocalDate deadlineEntry(Scanner input) {
        LocalDate date = null;
        while (date == null) {
            System.out.println("Enter task deadline (YYYY-MM-DD): ");
            try {
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
        Boolean repeat = null;
        String result = input.nextLine();

        while (repeat == null) {
            if (result.equalsIgnoreCase("yes")) {
                repeat = true;
            } else if (result.equalsIgnoreCase("no")) {
                repeat = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        return repeat;

    }

    /**
     * Saves tasks via ObjectOutputStream to activetasks.dat
     * Uses serialization to maintain objects within activeTaskList arraylist
     *
     */

    public static void saveTasks() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\Users\\rhyst\\IdeaProjects\\UniMate\\src\\main\\resources\\activetasks.dat"))) {
            output.writeObject(activeTaskList);
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
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream("C:\\Users\\rhyst\\IdeaProjects\\UniMate\\src\\main\\resources\\activetasks.dat"))) {
            activeTaskList = (ArrayList<Task>) input.readObject();
        }
        catch (ClassCastException | IOException | ClassNotFoundException e) {
            throw new ClassCastException(e.getMessage());
        }

    }

}
