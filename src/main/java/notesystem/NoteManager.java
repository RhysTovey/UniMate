package notesystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NoteManager {
    private final List<ModuleNotes> modules = new ArrayList<>();
    private final List<Note> allNotes = new ArrayList<>();

    /**
     * Accessed via CLI to create 'Module Folders' to hold notes in
     */

    public void addModule(ModuleNotes module) {
        modules.add(module);
    }

    public List<ModuleNotes > getModules() {
        return modules;
    }

    public Optional<ModuleNotes> getModuleByCode(String moduleCode) {
        return modules.stream().filter(module -> module.getModuleCode().equalsIgnoreCase(moduleCode)).findFirst();
    }

    public void addNoteToModule(String moduleCode, Note note) {
        getModuleByCode(moduleCode).ifPresent(module -> {
            module.addNote(note);
            note.setModuleCode(moduleCode);
            allNotes.add(note);
        });
    }

    public List<Note> getAllNotes() {
        return allNotes;
    }

    public boolean deleteModule(String moduleCode) {
        return modules.removeIf(module -> module.getModuleCode().equalsIgnoreCase(moduleCode));
    }



}
