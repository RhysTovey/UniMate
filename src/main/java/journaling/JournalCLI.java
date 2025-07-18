package journaling;

import java.time.LocalDate;
import java.util.Scanner;

public class JournalCLI {
    private Scanner input = new Scanner(System.in);
    private Journal journal;

    public JournalCLI(Journal journal) {
        this.journal = journal;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println(displayJournalMenu());
            switch (input.nextLine()) {
                case "1":
                    createJournalEntry();
                    break;
                case "2":
                    journal.iterateMap();
                    break;
                case "3":
                    viewJournalEntry();
                    break;

                case "0":
                    running = false;
                    System.out.println("Returning to main menu");
                    break;

                default:
                    System.out.println("Invalid input");

            }
        }

    }

    private void createJournalEntry() {
        System.out.println("Where are you journaling from? ");
        String location = input.nextLine();
        LocalDate date = LocalDate.now();
        System.out.println("Date recorded as: " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
        System.out.println("Entry contents: ");
        System.out.print(">> ");
        String entryContents = input.nextLine();

        JournalEntry entry = journal.createNewJournalEntry(location, entryContents, date);
        int month = date.getMonthValue();
        int year = date.getYear();
        journal.addJournalEntry(year, month, entry);
        System.out.println("Journal Entry recorded! ");

    }

    private void viewJournalEntry() {
        System.out.println("Please Select the Journal Year");
        journal.printYear();
        int year = input.nextInt();
        System.out.println(">> ");
        System.out.println("Journalled Months in " + year);
        System.out.println("Please Select the Journal Month");
        System.out.println(">> ");
        int month = input.nextInt();
        journal.printMonth(month);

    }


    private String displayJournalMenu() {
        return """
                1. New Journal Entry\s
                2. View Journal Entries\s
                3. Delete Journal Entry\s
                0. Return to Main Menu\s""";
    }
}
