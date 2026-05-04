import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends Person {
    public int year;
    public String studentId;

    private Researcher supervisor;
    private List<Mark> marks;

    public Student(String name, int year, String studentId) {
        super(name);
        this.year = year;
        this.studentId = studentId;
        this.marks = new ArrayList<>();
    }

    public void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
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
        return "Student{name='" + name + "', studentId='" + studentId +
               "', year=" + year + "}";
    }
}