package notesystem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String moduleCode; // reference to parent module
    private static final long serialVersionUID = 1L;

    public Note(String title, String content, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Optional: use this to set after adding to a module
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Note Contents:\n" + content + "\n" +
                "Created At: " + createdAt;
    }
}