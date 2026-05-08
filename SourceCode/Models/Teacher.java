package Models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {
    private List<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
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
        return this.getClass().getSimpleName() + "{username='" + this.getUsername() + "', dateEmployed=" + dateEmployed +
               ", courses=" + courses.size() + "}";
    }
}