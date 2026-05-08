package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String courseId;
    private String name;

    // Course knows about its lessons (arrow: Course → Lesson)
    private List<Lesson> lessons;
    private List<String> materialFiles;

    // Course knows about its instructors (arrow: Course → Teacher)
    private List<Person> instructors;

    public Course(String courseId, String name, List<Person> instructors, List<Lesson> lessons) {
        this.courseId = courseId;
        this.name = name;
        materialFiles = new ArrayList<>();
        this.instructors = instructors;
        this.lessons = lessons;
    }
    public Course(String courseId, String name, Person instructor) {
        this.courseId = courseId;
        this.name = name;
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
        materialFiles = new ArrayList<>();
        this.instructors.add(instructor);
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

    public List<String> getMaterialFiles()
    {
        return materialFiles;
    }

    public List<Person> getInstructors() {
        return instructors;
    }

    public String getName() {
        return name;
    }

    public String getCourseId() {
        return courseId;
    }
}
