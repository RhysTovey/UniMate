package notesystem;

import java.util.ArrayList;

public class ModuleNotes {
    /*
        Module Variables
     */

    private String moduleCode;
    private String moduleName;
    private ModuleDuration moduleDuration;
    private ArrayList<Note> moduleNotes;


    public ModuleNotes(String moduleCode, String moduleName, ModuleDuration moduleDuration, ArrayList<Note> moduleNotes) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDuration = moduleDuration;
        this.moduleNotes = moduleNotes;
    }

    /*
     * No arg-constructor for Note sub-class
     */
    public ModuleNotes() {

    }

    /*
     * Getters and Setters
     */

    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public ModuleDuration getModuleDuration() {
        return moduleDuration;
    }
    public void setModuleDuration(ModuleDuration moduleDuration) {
        this.moduleDuration = moduleDuration;
    }
    public ArrayList<Note> getModuleNotes() {
        return moduleNotes;
    }
    public void setModuleNotes(ArrayList<Note> moduleNotes) {
        this.moduleNotes = moduleNotes;
    }

    public void addModuleNote(Note note) {
        this.moduleNotes.add(note);
    }


    @Override
    public String toString() {
        return "*** Module ***" +"\n"
                + moduleCode + "\n"
                + moduleName + "\n"
                + moduleDuration + "\n"
                + moduleNotes.size() + " Recorded notes";
    }
    // Comparable by time_created



}
