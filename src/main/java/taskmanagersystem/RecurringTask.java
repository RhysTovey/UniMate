package taskmanagersystem;

import java.io.Serializable;
import java.time.LocalDate;

public class RecurringTask extends Task implements Serializable {

    private RecurrenceType recurrenceType;
    private LocalDate endDate;
    private LocalDate nextOccurenceDate;
    private static final long serialVersionUID = 1L;



    public RecurringTask(String title, String description, LocalDate createdDate, LocalDate deadline, boolean completed,
                         RecurrenceType recurrenceType, LocalDate endDate) {
        super(title, description, createdDate, deadline, completed);
        this.recurrenceType = recurrenceType;
        this.endDate = endDate;
        this.nextOccurenceDate = calculateRecurrenceDate(deadline);

    }

    private LocalDate calculateRecurrenceDate(LocalDate fromDeadline) {
        return switch (recurrenceType) {
            case DAILY -> fromDeadline.plusDays(1);
            case WEEKLY -> fromDeadline.plusWeeks(1);
            case MONTHLY -> fromDeadline.plusMonths(1);
            case YEARLY -> fromDeadline.plusYears(1);
        };
    }

    public void updateNextOccurenceDate() {
        this.nextOccurenceDate = calculateRecurrenceDate(nextOccurenceDate);
    }

    public LocalDate getNextOccurenceDate() {
        return nextOccurenceDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    @Override
    public String toString() {
        return "taskmanagersystem.Task occurs: " + recurrenceType + "\n" +
                "Until: " + endDate + "\n" +
                "Next occurs: " +  nextOccurenceDate + "\n";
    }
}
