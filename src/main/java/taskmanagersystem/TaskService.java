package taskmanagersystem;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskManager taskManager;

    public TaskService(TaskManager taskManager) {
        this.taskManager = taskManager;

    }

    public void createTask(String title, String desc, LocalDate startDate, LocalDate deadline, boolean isComplete) {
        Task task = new Task(title, desc, startDate, deadline, isComplete);
        taskManager.addTask(task);
    }

    public void createRecurringTask(String title, String desc, LocalDate startDate, LocalDate deadline, boolean isComplete,
                                    RecurrenceType type, LocalDate endDate) {
        RecurringTask task = new  RecurringTask(title, desc, startDate, deadline, isComplete, type, endDate);
        taskManager.addRecurringTask(task);
    }

    public List<Task> getActiveTask() {
        return taskManager.getActiveTasks();
    }
    public List<RecurringTask> getRecurringTask() {
        return taskManager.getRecurringTasks();
    }
    public List<Task> getCompleteTask() {
        return taskManager.getCompletedTasks();
    }


}
