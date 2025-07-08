import java.util.*;
import java.io.*;
import java.time.LocalDate;


public class Task {
    private String title;
    private String description;
    private LocalDate date;
    private LocalDate  deadline;
    private boolean repeats;
    private boolean isComplete;

    public Task(String title, String description, LocalDate date, LocalDate deadline, boolean repeats, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.deadline = deadline;
        this.repeats = repeats;
        this.isComplete = isComplete;
    }

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
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public boolean isRepeats() {
        return repeats;
    }
    public void setRepeats(boolean repeats) {
        this.repeats = repeats;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Task: " + getTitle() + "\n");
        builder.append("Description: " + getDescription() + "\n");
        builder.append("Date: " + getDate() + "\n");
        builder.append("Deadline: " + getDeadline() + "\n");
        builder.append("Repeats: " + isRepeats() + "\n");
        builder.append("Complete: " + isComplete() + "\n");
        return builder.toString();
    }




}
