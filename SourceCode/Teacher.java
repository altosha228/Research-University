import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {
    private List<Course> courses;

    public Teacher(String name, LocalDate dateEmployed) {
        super(name, dateEmployed);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public String toString() {
        return "Teacher{name='" + name + "', dateEmployed=" + dateEmployed +
               ", courses=" + courses.size() + "}";
    }
}