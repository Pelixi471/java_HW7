import java.time.LocalDate;

public class Event {

    LocalDate date;
    String name;
    String description;

    public Event(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public LocalDate date() {
        return this.date;
    }

    public  String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }
}
