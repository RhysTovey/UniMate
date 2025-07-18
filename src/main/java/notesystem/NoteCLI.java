package notesystem;

import fileRepo.FileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NoteCLI {
    /*
     * Class handles all CLI related I/O
     */

    private final Scanner scanner = new Scanner(System.in);
    private final NoteManager noteManager;

    public NoteCLI(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public void run() {
        try {
            noteManager.setAllNotes(FileRepository.load("src/main/resources/notes.dat"));
            noteManager.setModules(FileRepository.load("src/main/resources/modules.dat"));
            noteManager.populateModules();
        }catch(Exception e){
            System.err.println("Error loading notes file");
        }
        while (true) {
            System.out.println("""
                1. Create Module
                2. Create Note
                3. View Modules
                4. View All Notes
                5. Delete Module
                0. Exit
                """);
            System.out.print(">> ");
            switch (scanner.nextLine().trim()) {
                case "1" -> createModulePrompt();
                case "2" -> createNotePrompt();
                case "3" -> viewModules();
                case "4" -> viewAllNotes();
                case "5" -> deleteModulePrompt();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }

    private void createModulePrompt() {
        System.out.print("Enter Module Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Module Name: ");
        String name = scanner.nextLine();
        ModuleDuration duration = promptModuleDuration();

        ModuleNotes module = new ModuleNotes(code, name, duration);
        noteManager.addModule(module);
        System.out.println("Module created!");
    }

    private void createNotePrompt() {
        System.out.print("Enter Note Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Note Content: ");
        String content = scanner.nextLine();

        LocalDateTime created = LocalDateTime.now();
        Note note = new Note(title, content, created);

        viewModules();
        System.out.print("Enter module code to add note to: ");
        String moduleCode = scanner.nextLine();

        noteManager.addNoteToModule(moduleCode, note);
        System.out.println("Note added!");
    }

    private void viewModules() {
        List<ModuleNotes> modules = noteManager.getModules();
        if (modules.isEmpty()) {
            System.out.println("No modules yet.");
        } else {
            for (ModuleNotes m : modules) {
                System.out.println(m);
            }
        }
    }

    private void viewAllNotes() {
        List<Note> notes = noteManager.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes yet.");
        } else {
            for (Note note : notes) {
                System.out.println("Module Code: " + note.getModuleCode());
                System.out.println(note);
                System.out.println("*----------------*");
            }
        }
    }

    private void deleteModulePrompt() {
        System.out.print("Enter module code to delete: ");
        String moduleCode = scanner.nextLine();
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            boolean removed = noteManager.deleteModule(moduleCode);
            if (removed) {
                noteManager.modifyDeletedFiles(true);
            }
            System.out.println(removed ? "Module deleted." : "Module not found.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private ModuleDuration promptModuleDuration() {
        while (true) {
            System.out.println("""
                Select duration:
                1. Autumn Semester
                2. Spring Semester
                3. Academic Year
                """);
            System.out.print(">> ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                return switch (choice) {
                    case 1 -> ModuleDuration.AUTUMN_SEMESTER;
                    case 2 -> ModuleDuration.SPRING_SEMESTER;
                    case 3 -> ModuleDuration.ACADEMIC_YEAR;
                    default -> {
                        System.out.println("Invalid number. Try again.");
                        yield null;
                    }
                };
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}