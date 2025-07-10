import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private static List<Task> activeTaskList = new ArrayList<>();
    private static List<Task> completedTaskList = new ArrayList<>();

    public static List<Task> getActiveTaskList() {
        return activeTaskList;

    }
    public static List<Task> getCompletedTaskList() {
        return completedTaskList;
    }

    /**
     *
     * @param title
     * @param description
     * @param date
     * @param deadline
     * @param repeats
     * @param completed
     *
     * Takes params from CLI to create a new task, if complete is true adds to completed task list
     *
     */


    public static void createTask(String title, String description, LocalDate date, LocalDate deadline, boolean repeats, boolean completed) {
        try {
            Task newTask = new Task(title, description, date, deadline, repeats, completed);
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
     * Neatly prints task list for user, showing title, description deadline and ID
     */

    public static void printTaskList() {
        if  (activeTaskList.isEmpty()) {
            System.out.println("There are no active tasks");
        }
        for (Task task : activeTaskList) {
            System.out.println("<---- Task ID " + activeTaskList.indexOf(task) +  " ---->");
            System.out.println("Title: " + task.getTitle() + "\n"
                    + "Description: "+task.getDescription() + "\n" +
                    "Deadline: "+task.getDeadline().toString() + "\n");
        }
    }

    /**
     * removeTask
     * Checks if Tasks exist, if so, tasks are displayed and the user can type the ID and delete
     */
    public static void removeTask(String id) {
        int indexID = Integer.parseInt(id);
        if (!activeTaskList.isEmpty()) {
            activeTaskList.remove(indexID);
            System.out.println("Task " + indexID + " has been removed");
        }
        else {
            System.out.println("Task does not exist! ");
        }
    }

    /**
     * markComplete
     *
     * Takes an ID from user to mark this task as complete, sets complete to true and adds to completedTaskList
     */

    public static void markComplete(String id) {
        int indexID = Integer.parseInt(id);
        if (!activeTaskList.isEmpty()) {
            Task task = activeTaskList.get(indexID);
            task.setComplete(true);
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
        System.out.println("Enter task deadline (YYYY-MM-DD): ");
        String dl = input.nextLine();
        StringTokenizer st = new StringTokenizer(dl, "-");
        int year  = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        return LocalDate.of(year, month, day);

    }

    /**
     * @param input
     * @return
     *
     * Encapsulated logic for multiple re-use
     */

    public static boolean yesOrNo (Scanner input) {
        System.out.println("1. Yes\n2. No");
        return input.nextLine().equals("1");

    }

    /**
     * Saves tasks via ObjectOutputStream to tasks.dat
     * Uses serialization to maintain objects within activeTaskList arraylist
     *
     * @throws IOException
     */

    public static void saveTasks(){
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\Users\\rhyst\\IdeaProjects\\UniMate\\src\\main\\resources\\tasks.dat"))) {
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
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\rhyst\\IdeaProjects\\UniMate\\src\\main\\resources\\tasks.dat"))) {
            activeTaskList = (ArrayList<Task>) input.readObject();
        }
        catch (ClassCastException | IOException | ClassNotFoundException e) {
            throw new ClassCastException(e.getMessage());
        }


    }

}
