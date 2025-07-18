package journaling;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Journal {
    // Maps Int Year to a Map of months, which are mapped to Journal Entries
    private final Map<Integer, Map<Month, List<JournalEntry>>> journalEntries;

    public Journal() {
        journalEntries = new HashMap<>();
    }


    public void iterateMap() {
        for (Map.Entry<Integer, Map<Month, List<JournalEntry>>> entry : journalEntries.entrySet()) {
            System.out.println(entry.getKey());
            for (Map.Entry<Month, List<JournalEntry>> entryList : entry.getValue().entrySet()) {
                System.out.println(entryList.getKey());
                for (JournalEntry journalEntry : entryList.getValue()) {
                    System.out.println(journalEntry);
                }
            }

        }
    }

    public void printYear() {
        for  (Map.Entry<Integer, Map<Month, List<JournalEntry>>> entry : journalEntries.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public void printMonth(int month) {
        Month monthKey = Month.of(month);
        for (Map<Month, List<JournalEntry>> entry : journalEntries.values()) {
            entry.get(monthKey).forEach(journalEntry -> System.out.println(journalEntry));
        }
    }


    /**
     *
     *
     * @param month
     * @param journalEntry
     */

    public void addJournalEntry(int year,int month, JournalEntry journalEntry) {
        Month monthKey = Month.of(month);
        // Checks to return existing year key of inner map, if not creates new hashmap
        Map<Month, List<JournalEntry>> innerMap = journalEntries.computeIfAbsent(year, k -> new HashMap<>());
        // Checks If keyValue of monthKey exists, returns if so, else creates new Array List
        List<JournalEntry> list = innerMap.computeIfAbsent(monthKey, k -> new ArrayList<>());
        // Add individual entry to journal
        list.add(journalEntry);
    }


    public JournalEntry createNewJournalEntry(String location, String entryContents, LocalDate date) {
        return new JournalEntry(location, entryContents, date);
    }







}
