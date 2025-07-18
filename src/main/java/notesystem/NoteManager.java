package notesystem;

import java.time.LocalDateTime;
import java.util.*;

import fileRepo.FileRepository;

public class NoteManager {
    private static List<ModuleNotes> modules = new ArrayList<>();
    private static List<Note> allNotes = new ArrayList<>();

    /**
     * Accessed via CLI to create 'Module Folders' to hold notes in
     */

    public void addModule(ModuleNotes module) {
        modules.add(module);
        FileRepository.save("src/main/resources/modules.dat", modules);
    }

    public List<ModuleNotes > getModules() {
        return modules;
    }

    public void setModules(List<ModuleNotes> modules) {
        NoteManager.modules =  modules;
    }

    public void setAllNotes(List<Note> allNotes) {
        NoteManager.allNotes = allNotes;
    }

    public Optional<ModuleNotes> getModuleByCode(String moduleCode) {
        return modules.stream().filter(module -> module.getModuleCode().equalsIgnoreCase(moduleCode)).findFirst();
    }

    public void populateModules() {
        int index = 0;
        List<Note> notes = getAllNotes();
        for(int j = 0; j < notes.size(); j++) {
            Note note = notes.get(j);
            for (ModuleNotes module : modules) {
                if (module.getModuleCode().equalsIgnoreCase(note.getModuleCode())) {
                    module.addNote(note);
                }
            }
        }
    }

    public void addNoteToModule(String moduleCode, Note note) {
        getModuleByCode(moduleCode).ifPresent(module -> {
            module.addNote(note);
            note.setModuleCode(moduleCode);
            allNotes.add(note);
            FileRepository.save("src/main/resources/notes.dat", allNotes);
        });
    }

    public List<Note> getAllNotes() {
        return allNotes;
    }

    public void modifyDeletedFiles(Boolean modified) {
        FileRepository.save("src/main/resources/notes.dat", modules);
        FileRepository.save("src/main/resources/notes.dat", allNotes);

    }

    public boolean deleteModule(String moduleCode) {
        return modules.removeIf(module -> module.getModuleCode().equalsIgnoreCase(moduleCode));
    }



}
