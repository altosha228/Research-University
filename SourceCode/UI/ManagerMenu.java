package UI;
import db.DB;
import Models.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManagerMenu {
    private DB dbInstance;
    private Scanner sc;
    private Manager manager;
    public ManagerMenu(DB dbInstance, Scanner sc, Manager manager)
    {
        this.dbInstance = dbInstance;
        this.sc = sc;
        this.manager = manager;
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
                case "1": manageLessonsDialog(); 
                          showMenuCommands();
                          break;
                case "2": manageProjectsDialog(); 
                          showMenuCommands();
                          break;
                case "3": manageTeachersEnrollmentDialog(); 
                          showMenuCommands();
                          break;
                case "4": manageStudentsEnrollmentDialog(); 
                          showMenuCommands();
                          break;
                case "5": manageResearchersEnrollmentDialog(); 
                          showMenuCommands();
                          break;
                case "6": processRequests();
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
        System.out.println("[1] - Управление уроками");
        System.out.println("[2] - Управление исследовательскими проектами");
        System.out.println("[3] - Управление зачислением учителей");
        System.out.println("[4] - Управление зачислением студентов");
        System.out.println("[5] - Управление зачислением исследователей");
        System.out.println("[6] - Показать все заявки");
        System.out.println("[q] - Выйти");
    }
    private void showCourceMenuCommands()
    {
        System.out.println("--- Управление курсами ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать все курсы");
        System.out.println("[1] - Добавить новый курс");
        System.out.println("[2] - Удалить курс");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showLessonsMenuCommands()
    {
        System.out.println("--- Управление уроками ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать все уроки");
        System.out.println("[1] - Добавить новый урок");
        System.out.println("[2] - Удалить урок");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showProjectsMenuCommands()
    {
        System.out.println("--- Управление исследовательскими проектами ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать все проекты");
        System.out.println("[1] - Добавить новый проект");
        System.out.println("[2] - Удалить проект");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showTeacherEnrollmentMenuCommands()
    {
        System.out.println("--- Управление зачислением учителей ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать всех учителей");
        System.out.println("[1] - Зачислить учителя на курс");
        System.out.println("[2] - Исключить учителя с курса");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showStudentEnrollmentMenuCommands()
    {
        System.out.println("--- Управление зачислением студентов ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать всех студентов");
        System.out.println("[1] - Зачислить студента на курс");
        System.out.println("[2] - Исключить студента с курса");
        System.out.println("[q] - Вернуться в главное меню");
    }
    private void showResearcherEnrollmentMenuCommands()
    {
        System.out.println("--- Управление зачислением исследователей ---");
        System.out.println("Команды:");
        System.out.println("[0] - Показать всех исследователей");
        System.out.println("[1] - Зачислить исследователя на проект");
        System.out.println("[2] - Исключить исследователя с проекта");
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
                case "2": deleteCourse(); showMenuCommands(); break;
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
    public void deleteCourse() 
    {
        printCourses();
        System.out.print("Введите ID курса для удаления: ");
        String courseId = sc.nextLine();
        Course courseToRemove = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (courseToRemove == null) {
            System.out.println("Курс не найден!");
            return;
        }
        dbInstance.getCourses().remove(courseToRemove);
        System.out.println("Курс успешно удален!");
    }

    public void manageLessonsDialog()
    {
        showLessonsMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printLessons(); break;
                case "1": addLesson(); showLessonsMenuCommands(); break;
                case "2": deleteLesson(); showLessonsMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }
    private void printLessons() 
    {
        System.out.println("--- Список уроков ---");
        for (Course c : dbInstance.getCourses()) 
        {
            for (Lesson l : c.getLessons()) 
            {
                System.out.println("- " + l.getTopic() + " (Курс: " + c.getName() + ")");
            }
        }
    }
    private void addLesson() 
    {
        printCourses();
        System.out.print("Введите ID курса для добавления урока: ");
        String courseId = sc.nextLine();
        Course course = dbInstance.getCourses().stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (course == null) {
            System.out.println("Курс не найден!");
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
            System.out.println("Неверный тип урока!");
            return;
        }
        LessonType type = LessonType.values()[lessonType];
        Lesson newLesson = new Lesson(lessonTopic, lessonDate, type);
        course.addLesson(newLesson);
        System.out.println("Урок успешно добавлен в курс!");
    }
    private void deleteLesson()
    {
        printLessons();
        System.out.print("Введите название урока для удаления: ");
        String lessonTopic = sc.nextLine();
        for (Course c : dbInstance.getCourses()) 
        {
            c.getLessons().removeIf(l -> l.getTopic().equals(lessonTopic));
        }
        System.out.println("Урок успешно удален!");
    }

    public void manageProjectsDialog()
    {
        showProjectsMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printResearchProjects(); break;
                case "1": addResearchProject(); showProjectsMenuCommands(); break;
                case "2": deleteResearchProject(); showProjectsMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }
    public void printResearchProjects()
    {
        System.out.println("--- Список исследовательских проектов ---");
        for (ResearchProject p : dbInstance.getResearchProjects()) 
        {
            System.out.println("- " + p.getTopic() + " | Status: " + p.getStatus() + " | Participants: " + p.getProjectParticipants().size());
        }
    }
    private void addResearchProject() 
    {
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
            System.out.println("Исследователь не найден!");
            return;
        }
        ResearchProject newProject = new ResearchProject(topic, person.getResearcherProfile());
        dbInstance.getResearchProjects().add(newProject);
        System.out.println("Исследовательский проект успешно добавлен!");
    }
    private void deleteResearchProject() 
    {
        printResearchProjects();
        System.out.print("Введите название исследовательского проекта для удаления: ");
        String topic = sc.nextLine();
        ResearchProject projectToRemove = dbInstance.getResearchProjects().stream()
                .filter(p -> p.getTopic().equals(topic))
                .findFirst()
                .orElse(null);
        if (projectToRemove == null) {
            System.out.println("Проект не найден!");
            return;
        }
        dbInstance.getResearchProjects().remove(projectToRemove);
        System.out.println("Исследовательский проект успешно удален!");
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
                case "2": excludeTeacher(); showTeacherEnrollmentMenuCommands(); break;
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
        manager.addTeacherToCourse(teacher, course);
        System.out.println("Преподаватель успешно зачислен на курс!");
    }
    public void excludeTeacher()
    {
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

    public void manageStudentsEnrollmentDialog()
    {
        showStudentEnrollmentMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printStudents(); break;
                case "1": enrollStudent(); showStudentEnrollmentMenuCommands(); break;
                case "2": excludeStudent(); showStudentEnrollmentMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }
    public void printStudents() 
    {
        System.out.println("--- Список студентов ---");
        for (Person p : dbInstance.getPersons()) 
        {
            if (p instanceof Student) 
            {
                System.out.println("- " + p.getUsername());
            }
        }
    }
    public void enrollStudent()
    {
        printStudents();
        System.out.println("Введите имя студента для зачисления на курс: ");
        String username = sc.nextLine();
        Student student = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Student && p.getUsername().equals(username))
                .map(p -> (Student) p)
                .findFirst()
                .orElse(null);
        if (student == null) {
            System.out.println("Студент не найден!");
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
            System.out.println("Курс не найден!");
            return;
        }
        manager.addStudentToCourse(student, course);
        System.out.println("Студент успешно зачислен на курс!");
    }
    public void excludeStudent()
    {
        printStudents();
        System.out.println("Введите имя студента для исключения с курса: ");
        String username = sc.nextLine();
        Student student = dbInstance.getPersons().stream()
                .filter(p -> p instanceof Student && p.getUsername().equals(username))
                .map(p -> (Student) p)
                .findFirst()
                .orElse(null);
        if (student == null) {
            System.out.println("Студент не найден!");
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
            System.out.println("Курс не найден!");
            return;
        }
        manager.removeStudentFromCourse(student, course);
        System.out.println("Студент успешно исключен с курса!");
    }
    public void manageResearchersEnrollmentDialog()
    {
        showResearcherEnrollmentMenuCommands();
        while (true) 
        {
            System.out.print("> ");
            String input = sc.nextLine();
            switch (input) {
                case "q": return;
                case "0": printResearchers(); break;
                case "1": enrollResearcher(); showResearcherEnrollmentMenuCommands(); break;
                case "2": excludeResearcher(); showResearcherEnrollmentMenuCommands(); break;
                default: System.out.printf("Неизвестная команда: %s", input.trim()); break;
            }
        }
    }
    public void printResearchers() 
    {
        System.out.println("--- Список исследователей ---");
        for (Person p : dbInstance.getPersons()) 
        {
            if (p.getResearcherProfile() != null) 
            {
                System.out.println("- " + p.getUsername() + " | hIndex: " + p.getResearcherProfile().getHIndex());
            }
        }
    }

    public void enrollResearcher()
    {
        printResearchers();
        System.out.println("Введите имя исследователя для зачисления на проект: ");
        String username = sc.nextLine();
        Person person = dbInstance.getPersons().stream()
                .filter(p -> p.getUsername().equals(username) && p.getResearcherProfile() != null)
                .findFirst()
                .orElse(null);
        if (person == null) {
            System.out.println("Исследователь не найден!");
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
            System.out.println("Проект не найден!");
            return;
        }
        manager.addResearcherToProject(researcher, project);
        System.out.println("Исследователь успешно зачислен на проект!");
    }
    public void excludeResearcher()
    {
        printResearchers();
        System.out.println("Введите имя исследователя для исключения с проекта: ");
        String username = sc.nextLine();
        Person person = dbInstance.getPersons().stream()
                .filter(p -> p.getUsername().equals(username) && p.getResearcherProfile() != null)
                .findFirst()
                .orElse(null);
        if (person == null) {
            System.out.println("Исследователь не найден!");
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
            System.out.println("Проект не найден!");
            return;
        }
        manager.removeResearcherFromProject(researcher, project);
        System.out.println("Исследователь успешно исключен с проекта!");
    }

    public void processRequests()
    {
        System.out.println("--- Пришедшие заявки ---");
        for (Request r : dbInstance.getRequests()) 
        {
            if (!r.is_handled) {
                System.out.println("- " + r.short_desc + "| message: " + r.message + " | Created at: " + r.created_at);
            }
            r.is_handled = true;
            System.out.print("Принять? (y/n): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("y")) {
                r.apply();
                r.is_applied = true;
            } else {
                System.out.println("Причина отказа: ");
                String reason = sc.nextLine();
                r.rejection_reason = reason;
                System.out.println("Заявка отклонена.");
            }
        }
    }
}

