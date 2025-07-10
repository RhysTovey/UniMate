import java.time.LocalDate;

public class RecurringTask extends Task {

    public RecurringTask(String title, String description, LocalDate date, LocalDate deadline, boolean completed ) {
        super(title, description, date, deadline, completed);
    }


}
