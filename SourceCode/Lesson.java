
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Lesson {

    /*
     Default constructor
    */
    public Lesson() {
    }

    public String topic;
    public LocalDate date;
    public Set<Mark> marks;
    public LessonType type;
    public Course course;
}