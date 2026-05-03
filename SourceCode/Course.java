import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;

    // Course knows about its lessons (arrow: Course → Lesson)
    private List<Lesson> lessons = new ArrayList<>();

    // Course knows about its instructors (arrow: Course → Teacher)
    private List<Teacher> instructors = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addInstructor(Teacher teacher) {
        instructors.add(teacher);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Teacher> getInstructors() {
        return instructors;
    }

    public String getName() {
        return name;
    }
}
