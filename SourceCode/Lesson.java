import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String topic;
    private LocalDate date;
    private LessonType type;

    // One-to-many: Lesson has many Marks
    private List<Mark> marks = new ArrayList<>();

    // NOTE: No Course field here — the arrow on the diagram goes FROM Course TO Lesson,
    // meaning Course knows about Lesson, not the other way around.

    public Lesson(String topic, LocalDate date, LessonType type) {
        this.topic = topic;
        this.date = date;
        this.type = type;
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public LessonType getType() {
        return type;
    }
}
