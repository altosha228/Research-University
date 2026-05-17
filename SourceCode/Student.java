public class Student extends Person {
    private int year;
    private String studentId;

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
}