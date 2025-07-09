import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TaskManager {
    private static List<Task> activeTaskList = new ArrayList<>();
    private static List<Task> completedTaskList = new ArrayList<>();

    public static List<Task> getActiveTaskList() {
        return activeTaskList;

    }
    public static List<Task> getCompletedTaskList() {
        return completedTaskList;
    }

    public static void createTask() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter task name: ");
            String title = input.nextLine();
            System.out.println("Enter task description: ");
            String description = input.nextLine();
            System.out.println("Task created at: " + LocalDateTime.now());
            LocalDate date = LocalDate.now();
            LocalDate deadline = deadlineEntry(input);
            System.out.println("Will this task repeat? ");
            boolean repeats = yesOrNo(input);
            System.out.println("Have you already completed this task? ");
            boolean completed = yesOrNo(input);
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

    public static LocalDate deadlineEntry(Scanner input) {
        System.out.println("Enter task deadline (YYYY-MM-DD): ");
        String dl = input.nextLine();
        StringTokenizer st = new StringTokenizer(dl, "-");
        int year  = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        return LocalDate.of(year, month, day);

    }

    public static boolean yesOrNo (Scanner input) {
        System.out.println("1. Yes\n2. No");
        return input.nextLine().equals("1");

    }

    public static void saveTasks() throws IOException {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\rhyst\\IdeaProjects\\UniMate\\src\\main\\resources\\tasks.txt");
            int totalSize = activeTaskList.size() + completedTaskList.size();
            for (int i = 0; i < totalSize; i++) {
                if (!activeTaskList.isEmpty()) {
                    fw.write(activeTaskList.get(i).toString());
                    fw.write("\n");
                }
                else if (!completedTaskList.isEmpty()) {
                    fw.write(completedTaskList.get(i).toString());
                    fw.write("\n");
                }
            }
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



}
