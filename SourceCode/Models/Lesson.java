package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String topic;
    private LocalDate date;
    private LessonType type;
    private List<Mark> marks = new ArrayList<>();

    public Lesson(String topic, LocalDate date, LessonType type) {
        this.topic = topic;
        this.date = date;
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
    }

    @Override
    public String toString() {
        return topic + " | " + date + " | " + type;
    }
}
