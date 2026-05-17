package Models;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends Person {
    private int year;
    private String studentId;

    private Researcher supervisor;
    private List<Course> courses;
    private List<Mark> marks;

    public Student(String username, String password, int year, String studentId) {
        super(username, password);
        this.year = year;
        this.studentId = studentId;
    }

    public void addCourse(Course course) {
        System.out.println("Курс успешно добавлен студенту.");
    }

    public void removeCourse(Course course) {
        System.out.println("Курс успешно удален у студента.");
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
        this.marks = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void setSupervisor(Researcher supervisor) throws ValidationException{
        if (supervisor == null) {
            supervisor = null;
            return;
        }
        if (supervisor.getHIndex() < 3) {
            throw new ValidationException("researchers hIndex too low! (< 3)");
        }
        if (year < 4) {
            throw new ValidationException("student must be 4 year");
        }
        this.supervisor = supervisor;
    }

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    public void removeCourse(Course course)
    {
        courses.remove(course);
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public int getYear() {
        return year;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public List<Mark> getMarksByCourse(Course course) throws ValidationException {
        if (course == null) {
            throw new ValidationException("Course cannot be null");
        }
        return marks.stream()
                .filter(m -> m.getLesson() != null && course.getLessons().contains(m.getLesson()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{username='" + this.getUsername() + "', studentId='" + studentId +
               "', year=" + year + "}";
    }
}