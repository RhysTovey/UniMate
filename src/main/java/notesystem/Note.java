package notesystem;

import java.time.LocalDateTime;

public class Note extends ModuleNotes {
    /*
     * Instance variables
     */

    private String title;
    private String noteContent;
    private LocalDateTime date;

    public Note(String title, String noteContent, LocalDateTime date) {
        super();
        this.title = title;
        this.noteContent = noteContent;
        this.date = date;
    }

    /*
     * Getters and Setters
     */

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNoteContent() {
        return noteContent;
    }
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
