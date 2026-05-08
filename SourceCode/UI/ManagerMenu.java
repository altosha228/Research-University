package UI;
import db.DB;
import Models.*;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ManagerMenu {
    private DB dbInstance;
    private Scanner sc;
    public ManagerMenu(DB dbInstance, Scanner sc)
    {
        this.dbInstance = dbInstance;
        this.sc = sc;
    }

    public void display()
    {
        showMenuCommands();
        while (true)
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": manageCoursesDialog(); 
                          showMenuCommands();
                          break;
                case "1": manageTeachersEnrollmentDialog(); 
                          showMenuCommands();
                          break;
                default: System.out.printf("Неизвестная команда: %s%n", input.trim()); break;
            }
        }
    }

    private void showMenuCommands()
    {
        System.out.println("--- Меню менеджера ---");
        System.out.println("Команды:");
        System.out.println("[0] - Управление курсами");
        System.out.println("[1] - Управление зачислением учителей");
        System.out.println("[q] - Выйти");
    }
    private void showCourceMenuCommands()
    {
        System.out.println("--- Управление курсами ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать все курсы");
        System.out.println("[1] - Добавить новый курс");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showTeacherEnrollmentMenuCommands()
    {
        System.out.println("--- Управление зачислением учителей ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать всех учителей");
        System.out.println("[1] - Зачислить учителя на курс");
        System.out.println("[q] - Вернуться в главное меню");
    }

    public void manageCoursesDialog()
    {
        showCourceMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printCourses(); break;
                case "1": addCourse(); showMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }

    public void printCourses() 
    {
        // Заголовок таблицы
        // %-20s означает строку (string) шириной 20 символов с выравниванием по левому краю
        System.out.printf("%-20s | %-40s | %-30s%n", "Course Name", "Lessons", "Instructors");
        System.out.println("-".repeat(95)); // Разделительная линия

        for (Course c : dbInstance.getCourses()) 
        {
            // Собираем только названия уроков через запятую
            String lessonTitles = c.getLessons().stream()
                    .map(Lesson::getTopic) // Убедись, что в Lesson есть метод getName() или getTitle()
                    .collect(Collectors.joining(", "));

            // Собираем только имена инструкторов
            String instructorNames = c.getInstructors().stream()
                    .map(Person::getUsername) // Убедись, что в Teacher/Person есть метод getName()
                    .collect(Collectors.joining(", "));

            // Вывод строки данных
            System.out.printf("%-20s | %-40s | %-30s%n", 
                c.getName(), 
                lessonTitles.length() > 37 ? lessonTitles.substring(0, 37) + "..." : lessonTitles, 
                instructorNames.length() > 27 ? instructorNames.substring(0, 27) + "..." : instructorNames
            );
        }
    }

    public void addCourse() 
    {
        System.out.print("Введите название курса: ");
        String courseName = sc.nextLine();
        System.out.print("Введите ID курса: ");
        String courseId = sc.nextLine();
        printTeachers();
        System.out.println("Введите имя преподавателя для курса: ");
        String teacherName = sc.nextLine();
        Teacher teacher = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Teacher && p.getUsername().equals(teacherName))
                .map(p -> (Teacher) p)
                .findFirst()
                .orElse(null);
        if (teacher == null) {
            System.out.println("Преподаватель не найден!");
            return;
        }
        Course newCourse = new Course(courseName, courseId, teacher);
        dbInstance.getCourses().add(newCourse);
        System.out.println("Курс успешно добавлен!");
    }

    public void manageTeachersEnrollmentDialog()
    {
        showTeacherEnrollmentMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printTeachers(); break;
                case "1": enrollTeacher(); showTeacherEnrollmentMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }

    public void printTeachers() 
    {
        System.out.println("--- Список преподавателей ---");
        for (Person p : dbInstance.getPersons()) 
        {
            if (p instanceof Teacher) 
            {
                System.out.println("- " + p.getUsername());
            }
        }
    }

    public void enrollTeacher()
    {
        printCourses();
        System.out.println("Введите ID курса для зачисления преподавателя: ");
        String courseId = sc.nextLine();
        Course course = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (course == null) {
            System.out.println("Курс не найден!");
            return;
        }
        printTeachers();
        System.out.println("Введите имя преподавателя для зачисления на курс: ");
        String teacherName = sc.nextLine();
        Teacher teacher = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Teacher && p.getUsername().equals(teacherName))
                .map(p -> (Teacher) p)
                .findFirst()
                .orElse(null);
        if (teacher == null) {
            System.out.println("Преподаватель не найден!");
            return;
        }
        course.addInstructor(teacher);
        System.out.println("Преподаватель успешно зачислен на курс!");
    }
}
