package notesystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteManager {
    private static ArrayList<ModuleNotes> modulesFolder = new ArrayList<>();

    /**
     * Accessed via CLI to create 'Module Folders' to hold notes in
     */

    public static void createModule() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Module Code: ");
        String moduleCode = input.nextLine();
        System.out.print("Enter Module Name: ");
        String moduleName = input.nextLine();
        System.out.println("Enter Module Duration: ");
        ModuleDuration duration = moduleDurationPrompt();
        ArrayList<Note> moduleNotes = new ArrayList<>();
        ModuleNotes newMod = new ModuleNotes(moduleCode, moduleName, duration, moduleNotes);
        modulesFolder.add(newMod);
        System.out.println("Module Created");
    }

    public static void viewModules() {
        for (ModuleNotes moduleNote : modulesFolder) {
            System.out.println(moduleNote.toString());
        }
    }

    private static void chooseFolderToAdd(Note note) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Module Code: ");
        String moduleCode = input.nextLine();
        System.out.print(">> ");
        try {
            for (ModuleNotes moduleNote : modulesFolder) {
                if(moduleNote.getModuleCode().trim().equalsIgnoreCase(moduleCode.trim())) {
                    moduleNote.addModuleNote(note);
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("Invalid Module Code");
            chooseFolderToAdd(note);
        }

    }

    public static void createNote() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Note Title: ");
        String noteTitle = input.nextLine();
        System.out.println("Enter Note Content: ");
        String noteDescription = input.nextLine();
        System.out.println("Note Created at " + LocalDateTime.now());
        Note newNote = new Note(noteTitle, noteDescription,  LocalDateTime.now());
        viewModules();
        chooseFolderToAdd(newNote);
    }

    public static void viewAllNotes() {

    }
    
    private static ModuleDuration moduleDurationPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("""
                1. Autumn Semester \s
                2. Spring Semester \s
                3. Academic Year \s
                """);
        System.out.print(">> ");
        try {
            return switch (input.nextInt()) {
                case 1 -> ModuleDuration.AUTUMN_SEMESTER;
                case 2 -> ModuleDuration.SPRING_SEMESTER;
                case 3 -> ModuleDuration.ACADEMIC_YEAR;
                default -> null;
            };
        } catch (Exception e) {
            System.out.println(
                    "/// Error ///" + "\n" +
                    "Input not recognized");
            moduleDurationPrompt();
        }
        return null;
    }
}
