package journaling;

import java.time.LocalDate;

public class JournalEntry {
    private String location;
    private String entryContents;
    private LocalDate date;

    public JournalEntry(String location, String entryContents,LocalDate date) {
        this.location = location;
        this.entryContents = entryContents;
        this.date = LocalDate.now();
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String dateToString(LocalDate date) {
        return date.getMonth().toString() + " " + date.getDayOfMonth() + " " + date.getYear();
    }
}
