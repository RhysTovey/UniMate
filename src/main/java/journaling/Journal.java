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

    public Map<Map<Month, List<JournalEntry>>, List<Integer>> getJournalEntries() {
        return journalEntries.keySet().stream().collect(Collectors.groupingBy(journalEntries::get));
    }

    /**
     *
     *
     * @param month
     * @param journalEntry
     */

    public void addJournalEntry(int year,int month, JournalEntry journalEntry) {
        Month monthKey = Month.of(month);

        // Create Inner Map with key month if it doesn't already exist
        Map<Month, List<JournalEntry>> innerMap = journalEntries.getOrDefault(monthKey, new HashMap<>());

        // If a list doesn't already exist to the associated key(val), will compute below
        List<JournalEntry> list = innerMap.computeIfAbsent(monthKey, k -> new ArrayList<>());
        // Add associated entry
        list.add(journalEntry);
        // Put to innerMap and outerMap
        innerMap.put(monthKey, list);
        journalEntries.put(year, innerMap);

    }

//    public Map<Integer, Map<Month, List<JournalEntry>>> getJournalEntries() {
//        return journalEntries.keySet().stream().collect(Collectors.toMap(k -> k, k -> new HashMap<>()));
//    }

    public JournalEntry createNewJournalEntry(String location, String entryContents, LocalDate date) {
        return new JournalEntry(location, entryContents, date);
    }







}
