package taskmanagersystem;

import java.io.*;
import java.time.LocalDate;

/**
 * taskmanagersystem.Task Class
 * To create taskmanagersystem.Task objects for use within the taskmanagersystem.TaskManager
 * Implements Serializable to read/write into/from activetasks.dat
 */


public class Task implements Serializable, Comparable<Task> {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;
    private boolean isComplete;
    @Serial
    private static final long serialVersionUID = 1L;

    public Task(String title, String description, LocalDate startDate, LocalDate deadline, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.isComplete = isComplete;
    }

    /*
        Getters and Setters for taskmanagersystem.Task IV's
     */

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public LocalDate setDeadline(LocalDate deadline) {
        return this.deadline = deadline;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    /**
     *
     * @return neatly arranged String of variables
     */

    @Override
    public String toString() {
        return "taskmanagersystem.Task: " + title + "\n" +
                "Description: " + description + "\n" +
                "Date: " + startDate + "\n" +
                "Deadline: " + deadline + "\n" +
                "IsComplete: " + isComplete + "\n";
    }


    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Task o) {
        return this.deadline.compareTo(o.deadline);
    }
}
