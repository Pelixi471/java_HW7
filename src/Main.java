import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Main {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Event> readEventsFromFile(String filename) throws Exception {
        List<Event> events = new ArrayList<>();
        Path path = Path.of(filename).toAbsolutePath();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" name: | description: ");
                LocalDate date = LocalDate.parse(parts[0].substring(6), FORMATTER);
                String name = parts[1];
                String description = parts[2];
                events.add(new Event(date, name, description));
            }
        }

        return events;
    }

    public static void writeEventsToFile(String filename, List<Event> events) throws Exception {
        Path path = Path.of(filename).toAbsolutePath();

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Event event : events) {
                writer.write(String.format("date: %s name: %s description: %s%n",
                        event.date().toString(), event.name(), event.description()));
            }
        }
    }

    public static void main(String[] args) {
        try {
            String filename_not_sorted  = "events_not_sorted.txt";
            String filename_sorted = "events_sorted.txt";
            List<Event> events = readEventsFromFile(filename_not_sorted);

            Collections.sort(events, (e1, e2) -> e1.date().compareTo(e2.date()));

            writeEventsToFile(filename_sorted, events);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}