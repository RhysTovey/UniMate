package notesystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModuleNotes implements Serializable {
    private String moduleCode;
    private String moduleName;
    private ModuleDuration moduleDuration;
    private final List<Note> notes;
    private static final long serialVersionUID = 1L;

    public ModuleNotes(String code, String name, ModuleDuration duration) {
        this.moduleCode = code;
        this.moduleName = name;
        this.moduleDuration = duration;
        this.notes = new ArrayList<>();
    }

    // Optional: for frameworks or deserialization
    public ModuleNotes() {
        this.notes = new ArrayList<>();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ModuleDuration getModuleDuration() {
        return moduleDuration;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
        note.setModuleCode(moduleCode);
    }

    @Override
    public String toString() {
        return "*** Module ***\n" +
                moduleCode + "\n" +
                moduleName + "\n" +
                moduleDuration + "\n" +
                notes.size() + " recorded notes";
    }
}