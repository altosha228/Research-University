package UI;

import db.DB;
import util.constants;
import Models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManagerMenu {
    private DB dbInstance;
    private Scanner sc;
    private Manager manager;

    public ManagerMenu(DB dbInstance, Scanner sc, Manager manager) {
        this.dbInstance = dbInstance;
        this.sc = sc;
        this.manager = manager;
    }

    public void display() {
        showMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    manageCoursesDialog();
                    showMenuCommands();
                    break;
                case "1":
                    manageLessonsDialog();
                    showMenuCommands();
                    break;
                case "2":
                    manageProjectsDialog();
                    showMenuCommands();
                    break;
                case "3":
                    manageTeachersEnrollmentDialog();
                    showMenuCommands();
                    break;
                case "4":
                    manageStudentsEnrollmentDialog();
                    showMenuCommands();
                    break;
                case "5":
                    manageResearchersEnrollmentDialog();
                    showMenuCommands();
                    break;
                case "6":
                    processRequests();
                    showMenuCommands();
                    break;
                default:
                    System.out.printf("Неизвестная команда: %s%n", input.trim());
                    break;
            }
        }
    }

    private void showMenuCommands() {
        System.out.println("--- Меню менеджера ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Управление курсами");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Управление уроками");
        System.out.println(
                constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Управление исследовательскими проектами");
        System.out.println(constants.ANSI_YELLOW + "[3]" + constants.ANSI_RESET + " - Управление зачислением учителей");
        System.out
                .println(constants.ANSI_YELLOW + "[4]" + constants.ANSI_RESET + " - Управление зачислением студентов");
        System.out.println(
                constants.ANSI_YELLOW + "[5]" + constants.ANSI_RESET + " - Управление зачислением исследователей");
        System.out.println(constants.ANSI_YELLOW + "[6]" + constants.ANSI_RESET + " - Показать все заявки");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Выйти");
    }

    private void showCourceMenuCommands() {
        System.out.println("--- Управление курсами ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать все курсы");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Добавить новый курс");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Удалить курс");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    private void showLessonsMenuCommands() {
        System.out.println("--- Управление уроками ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать все уроки");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Добавить новый урок");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Удалить урок");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    private void showProjectsMenuCommands() {
        System.out.println("--- Управление исследовательскими проектами ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать все проекты");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Добавить новый проект");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Удалить проект");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    private void showTeacherEnrollmentMenuCommands() {
        System.out.println("--- Управление зачислением учителей ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать всех учителей");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Зачислить учителя на курс");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Исключить учителя с курса");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    private void showStudentEnrollmentMenuCommands() {
        System.out.println("--- Управление зачислением студентов ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать всех студентов");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Зачислить студента на курс");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Исключить студента с курса");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    private void showResearcherEnrollmentMenuCommands() {
        System.out.println("--- Управление зачислением исследователей ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать всех исследователей");
        System.out
                .println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Зачислить исследователя на проект");
        System.out
                .println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Исключить исследователя с проекта");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Вернуться в главное меню");
    }

    public void manageCoursesDialog() {
        showCourceMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printCourses();
                    break;
                case "1":
                    addCourse();
                    showCourceMenuCommands();
                    break;
                case "2":
                    deleteCourse();
                    showCourceMenuCommands();
                    break;
                default:
                    System.out.printf(constants.ANSI_RED + "Неизвестная команда: %s%n" + constants.ANSI_RESET,
                            input.trim());
                    break;
            }
        }
    }

    public void printCourses() {
        // Заголовок таблицы
        // %-20s означает строку (string) шириной 20 символов с выравниванием по левому
        // краю
        System.out.printf("%-20s | %-40s | %-30s%n", "Course Name", "Lessons", "Instructors");
        System.out.println("-".repeat(95)); // Разделительная линия

        for (Course c : dbInstance.getCourses()) {
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
                    instructorNames.length() > 27 ? instructorNames.substring(0, 27) + "..." : instructorNames);
        }
    }

    public void addCourse() {
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
            System.out.println(constants.ANSI_RED + "Преподаватель не найден!" + constants.ANSI_RESET);
            return;
        }
        Course newCourse = new Course(courseName, courseId, teacher);
        dbInstance.getCourses().add(newCourse);
        System.out.println(constants.ANSI_GREEN + "Курс успешно добавлен!" + constants.ANSI_RESET);
    }

    public void deleteCourse() {
        printCourses();
        System.out.print("Введите ID курса для удаления: ");
        String courseId = sc.nextLine();
        Course courseToRemove = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (courseToRemove == null) {
            System.out.println(constants.ANSI_RED + "Курс не найден!" + constants.ANSI_RESET);
            return;
        }
        dbInstance.getCourses().remove(courseToRemove);
        System.out.println(constants.ANSI_GREEN + "Курс успешно удален!" + constants.ANSI_RESET);
    }

    public void manageLessonsDialog() {
        showLessonsMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printLessons();
                    break;
                case "1":
                    addLesson();
                    showLessonsMenuCommands();
                    break;
                case "2":
                    deleteLesson();
                    showLessonsMenuCommands();
                    break;
                default:
                    System.out.printf(constants.ANSI_RED + "Неизвестная команда: %s" + constants.ANSI_RESET,
                            input.trim());
                    break;
            }
        }
    }

    private void printLessons() {
        System.out.println("--- Список уроков ---");
        for (Course c : dbInstance.getCourses()) {
            for (Lesson l : c.getLessons()) {
                System.out.println("- " + l.getTopic() + " (Курс: " + c.getName() + ")");
            }
        }
    }

    private void addLesson() {
        printCourses();
        System.out.print("Введите ID курса для добавления урока: ");
        String courseId = sc.nextLine();
        Course course = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (course == null) {
            System.out.println(constants.ANSI_RED + "Курс не найден!" + constants.ANSI_RESET);
            return;
        }
        System.out.print("Введите название урока: ");
        String lessonTopic = sc.nextLine();
        System.out.println("Введите дату урока (в формате YYYY-MM-DD): ");
        String input = sc.nextLine();
        LocalDate lessonDate = LocalDate.parse(input);
        System.out.println("Введите тип урока ([0] - Lecture, [1] - Practice): ");
        String inp = sc.nextLine();
        int lessonType = Integer.parseInt(inp);
        if (lessonType >= LessonType.values().length || lessonType < 0) {
            System.out.println(constants.ANSI_RED + "Неверный тип урока!" + constants.ANSI_RESET);
            return;
        }
        LessonType type = LessonType.values()[lessonType];
        Lesson newLesson = new Lesson(lessonTopic, lessonDate, type);
        course.addLesson(newLesson);
        System.out.println(constants.ANSI_GREEN + "Урок успешно добавлен в курс!" + constants.ANSI_RESET);
    }

    private void deleteLesson() {
        printLessons();
        System.out.print("Введите название урока для удаления: ");
        String lessonTopic = sc.nextLine();

        boolean removed = false;

        for (Course c : dbInstance.getCourses()) {
            // removeIf возвращает true, если элемент был найден и удален
            if (c.getLessons().removeIf(l -> l.getTopic().equalsIgnoreCase(lessonTopic))) {
                removed = true;
            }
        }

        if (removed) {
            dbInstance.save(); // Не забываем сохранить изменения!
            System.out.println(constants.ANSI_GREEN + "Урок успешно удален!" + constants.ANSI_RESET);
        } else {
            System.out.println(constants.ANSI_RED + "Ошибка: Урок с таким названием не найден." + constants.ANSI_RESET);
        }
    }

    public void manageProjectsDialog() {
        showProjectsMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printResearchProjects();
                    break;
                case "1":
                    addResearchProject();
                    showProjectsMenuCommands();
                    break;
                case "2":
                    deleteResearchProject();
                    showProjectsMenuCommands();
                    break;
                default:
                    System.out.printf(constants.ANSI_RED + "Неизвестная команда: %s" + constants.ANSI_RESET,
                            input.trim());
                    break;
            }
        }
    }

    public void printResearchProjects() {
        System.out.println("--- Список исследовательских проектов ---");
        for (ResearchProject p : dbInstance.getResearchProjects()) {
            System.out.println("- " + p.getTopic() + " | Status: " + p.getStatus() + " | Participants: "
                    + p.getProjectParticipants().size());
        }
    }

    private void addResearchProject() {
        System.out.print("Введите название исследовательского проекта: ");
        String topic = sc.nextLine();
        printResearchers();
        System.out.println("Введите имя ведущего исследователя для проекта: ");
        String researcherName = sc.nextLine();
        Person person = dbInstance.getPersons().stream()
                .filter(p -> p.getUsername().equals(researcherName) && p.getResearcherProfile() != null)
                .findFirst()
                .orElse(null);
        if (person == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
            return;
        }
        ResearchProject newProject = new ResearchProject(topic, person.getResearcherProfile());
        dbInstance.getResearchProjects().add(newProject);
        System.out.println(constants.ANSI_GREEN + "Исследовательский проект успешно добавлен!" + constants.ANSI_RESET);
    }

    private void deleteResearchProject() {
        printResearchProjects();
        System.out.print("Введите название исследовательского проекта для удаления: ");
        String topic = sc.nextLine();
        ResearchProject projectToRemove = dbInstance.getResearchProjects().stream()
                .filter(p -> p.getTopic().equals(topic))
                .findFirst()
                .orElse(null);
        if (projectToRemove == null) {
            System.out.println(constants.ANSI_RED + "Проект не найден!" + constants.ANSI_RESET);
            return;
        }
        dbInstance.getResearchProjects().remove(projectToRemove);
        System.out.println(constants.ANSI_GREEN + "Исследовательский проект успешно удален!" + constants.ANSI_RESET);
    }

    public void manageTeachersEnrollmentDialog() {
        showTeacherEnrollmentMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printTeachers();
                    break;
                case "1":
                    enrollTeacher();
                    showTeacherEnrollmentMenuCommands();
                    break;
                case "2":
                    excludeTeacher();
                    showTeacherEnrollmentMenuCommands();
                    break;
                default:
                    System.out.printf("Неизвестная команда: %s", input.trim());
                    break;
            }
        }
    }

    public void printTeachers() {
        System.out.println("--- Список преподавателей ---");
        for (Person p : dbInstance.getPersons()) {
            if (p instanceof Teacher) {
                System.out.println("- " + p.getUsername());
            }
        }
    }

    public void enrollTeacher() {
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
        manager.addTeacherToCourse(teacher, course);
        System.out.println("Преподаватель успешно зачислен на курс!");
    }

    public void excludeTeacher() {
        printCourses();
        System.out.println("Введите ID курса для исключения преподавателя: ");
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
        System.out.println("Введите имя преподавателя для исключения с курса: ");
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
        manager.removeTeacherFromCourse(teacher, course);
        System.out.println("Преподаватель успешно исключен с курса!");
    }

    public void manageStudentsEnrollmentDialog() {
        showStudentEnrollmentMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printStudents();
                    break;
                case "1":
                    enrollStudent();
                    showStudentEnrollmentMenuCommands();
                    break;
                case "2":
                    excludeStudent();
                    showStudentEnrollmentMenuCommands();
                    break;
                default:
                    System.out.printf(constants.ANSI_RED + "Неизвестная команда: %s" + constants.ANSI_RESET,
                            input.trim());
                    break;
            }
        }
    }

    public void printStudents() {
        System.out.println("--- Список студентов ---");
        for (Person p : dbInstance.getPersons()) {
            if (p instanceof Student) {
                System.out.println("- " + p.getUsername());
            }
        }
    }

    public void enrollStudent() {
        printStudents();
        System.out.println("Введите имя студента для зачисления на курс: ");
        String username = sc.nextLine();
        Student student = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Student && p.getUsername().equals(username))
                .map(p -> (Student) p)
                .findFirst()
                .orElse(null);
        if (student == null) {
            System.out.println(constants.ANSI_RED + "Студент не найден!" + constants.ANSI_RESET);
            return;
        }
        printCourses();
        System.out.println("Введите ID курса для зачисления студента: ");
        String courseId = sc.nextLine();
        Course course = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (course == null) {
            System.out.println(constants.ANSI_RED + "Курс не найден!" + constants.ANSI_RESET);
            return;
        }
        manager.addStudentToCourse(student, course);
        System.out.println(constants.ANSI_GREEN + "Студент успешно зачислен на курс!" + constants.ANSI_RESET);
    }

    public void excludeStudent() {
        printStudents();
        System.out.println("Введите имя студента для исключения с курса: ");
        String username = sc.nextLine();
        Student student = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Student && p.getUsername().equals(username))
                .map(p -> (Student) p)
                .findFirst()
                .orElse(null);
        if (student == null) {
            System.out.println(constants.ANSI_RED + "Студент не найден!" + constants.ANSI_RESET);
            return;
        }
        printCourses();
        System.out.println("Введите ID курса для исключения студента: ");
        String courseId = sc.nextLine();
        Course course = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (course == null) {
            System.out.println(constants.ANSI_RED + "Курс не найден!" + constants.ANSI_RESET);
            return;
        }
        manager.removeStudentFromCourse(student, course);
        System.out.println(constants.ANSI_GREEN + "Студент успешно исключен с курса!" + constants.ANSI_RESET);
    }

    public void manageResearchersEnrollmentDialog() {
        showResearcherEnrollmentMenuCommands();
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q":
                    return;
                case "0":
                    printResearchers();
                    break;
                case "1":
                    enrollResearcher();
                    showResearcherEnrollmentMenuCommands();
                    break;
                case "2":
                    excludeResearcher();
                    showResearcherEnrollmentMenuCommands();
                    break;
                default:
                    System.out.printf(constants.ANSI_RED + "Неизвестная команда: %s" + constants.ANSI_RESET,
                            input.trim());
                    break;
            }
        }
    }

    public void printResearchers() {
        System.out.println("--- Список исследователей ---");
        for (Person p : dbInstance.getPersons()) {
            if (p.getResearcherProfile() != null) {
                System.out.println("- " + p.getUsername() + " | hIndex: " + p.getResearcherProfile().getHIndex());
            }
        }
    }

    public void enrollResearcher() {
        printResearchers();
        System.out.println("Введите имя исследователя для зачисления на проект: ");
        String username = sc.nextLine();
        Person person = dbInstance.getPersons().stream()
                .filter(p -> p.getUsername().equals(username) && p.getResearcherProfile() != null)
                .findFirst()
                .orElse(null);
        if (person == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
            return;
        }
        Researcher researcher = person.getResearcherProfile();
        printResearchProjects();
        System.out.println("Введите название проекта для зачисления исследователя: ");
        String projectName = sc.nextLine();
        ResearchProject project = dbInstance.getResearchProjects().stream()
                .filter(p -> p.getTopic().equals(projectName))
                .findFirst()
                .orElse(null);
        if (project == null) {
            System.out.println(constants.ANSI_RED + "Проект не найден!" + constants.ANSI_RESET);
            return;
        }
        manager.addResearcherToProject(researcher, project);
        System.out.println(constants.ANSI_GREEN + "Исследователь успешно зачислен на проект!" + constants.ANSI_RESET);
    }

    public void excludeResearcher() {
        printResearchers();
        System.out.println("Введите имя исследователя для исключения с проекта: ");
        String username = sc.nextLine();
        Person person = dbInstance.getPersons().stream()
                .filter(p -> p.getUsername().equals(username) && p.getResearcherProfile() != null)
                .findFirst()
                .orElse(null);
        if (person == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
            return;
        }
        Researcher researcher = person.getResearcherProfile();
        printResearchProjects();
        System.out.println("Введите название проекта для исключения исследователя: ");
        String projectName = sc.nextLine();
        ResearchProject project = dbInstance.getResearchProjects().stream()
                .filter(p -> p.getTopic().equals(projectName))
                .findFirst()
                .orElse(null);
        if (project == null) {
            System.out.println(constants.ANSI_RED + "Проект не найден!" + constants.ANSI_RESET);
            return;
        }
        manager.removeResearcherFromProject(researcher, project);
        System.out.println(constants.ANSI_GREEN + "Исследователь успешно исключен с проекта!" + constants.ANSI_RESET);
    }

    public void processRequests() {
        List<Request> requests = dbInstance.getRequests().stream().filter(r -> r.is_handled == false).toList();
        if (requests.size() == 0) {
            System.out.println(constants.ANSI_RED + "Нет заявок для обработки." + constants.ANSI_RESET);
            return;
        }
        System.out.println("--- Пришедшие заявки ---");
        for (Request r : requests) {
            if (r.is_handled) {
                continue;
            }
            System.out.println("- " + r.short_desc + "| message: " + r.message + " | Created at: " + r.created_at);
            r.is_handled = true;
            System.out.print("Принять? (y/n): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("y")) {
                r.apply();
                r.is_applied = true;
            } else {
                System.out.println(constants.ANSI_RED + "Причина отказа: " + constants.ANSI_RESET);
                String reason = sc.nextLine();
                r.rejection_reason = reason;
                System.out.println(constants.ANSI_RED + "Заявка отклонена." + constants.ANSI_RESET);
            }
        }
    }
}
