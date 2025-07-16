package taskmanagersystem;

import java.io.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskManager {
    private static List<Task> activeTasks = new ArrayList<>();
    private static List<Task> completedTasks = new ArrayList<>();
    private static List<RecurringTask> recurringTasks = new ArrayList<>();

    public List<Task> getActiveTasks() { return activeTasks; }
    public List<Task> getCompletedTasks() { return completedTasks; }
    public List<RecurringTask> getRecurringTasks() { return recurringTasks; }


    public void addTask(Task task) {
        activeTasks.add(task);
    }

    public void addRecurringTask(RecurringTask task) {
        recurringTasks.add(task);
    }

    public void markTaskComplete (int index) {
        Task task = activeTasks.remove(index);
        task.setComplete(true);
        completedTasks.add(task);
    }

    public void removeTask(List<? extends Task> list, int index) {
        list.remove(index);
    }

}
