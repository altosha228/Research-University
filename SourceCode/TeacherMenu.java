import java.util.Scanner;

public class TeacherMenu {
    private Teacher teacher; 

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
    }

    public void printCourses() {
        System.out.println("Курсы преподавателя: " + teacher.getUsername());
    }

    public void printMarks() {
        System.out.println("Экран просмотра журнала оценок.");
    }

    public void manageCourseMaterialDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Управление материалами ---");
        System.out.println("1. Добавить материал");
        System.out.println("2. Удалить материал");
        System.out.print("Выбор: ");
        int choice = scanner.nextInt();

        Course course = new Course();
        if (choice == 1) {
            teacher.addCourseMaterial("Lecture1.pdf", course);
        } else if (choice == 2) {
            teacher.removeCourseMaterial("Lecture1.pdf", course);
        }
    }

    public void manageInstructorDialog() {
        System.out.println("--- Управление инструкторами ---");
        teacher.addInstructor(new Course());
    }

    public void putMarkDialog() {
        System.out.println("--- Выставление оценки ---");
        Student dummyStudent = new Student("ivan_student", "123", 2, "ST12345");
        teacher.putMark(new Lesson(), dummyStudent, 95);
    }

    public void enrollToCourseDialog() {
        System.out.println("Регистрация преподавателя на курс выполненна.");
    }
}