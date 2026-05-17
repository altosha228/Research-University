package Models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {

    public Teacher(String username, String password, java.time.LocalDate dateEmployed) {
        super(username, password, dateEmployed);
    public Teacher(String username, String password) {
        super(username, password);
        this.courses = new ArrayList<>();
    }

    public void addCourseMaterial(String file, Course course) {
        System.out.println("Материал " + file + " добавлен к курсу.");
    }

    public void removeCourseMaterial(String file, Course course) {
        System.out.println("Материал " + file + " удален из курса.");
    }

    public void addInstructor(Course course) {
        System.out.println("Инструктор привязан к курсу.");
    }

    public void removeInstructor(Person instructor) {
        System.out.println("Инструктор " + instructor.getUsername() + " удален.");
    }

    public void putMark(Lesson lesson, Student student, int value) {
        System.out.println("Студенту " + student.getUsername() + " выставлена оценка " + value);
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{username='" + this.getUsername() + "', dateEmployed=" + dateEmployed +
               ", courses=" + courses.size() + "}";
    }
}