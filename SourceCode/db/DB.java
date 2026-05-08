package db;
import java.io.*;
import java.util.*;
import Models.*;

public class DB implements Serializable {
    // Серийный номер версии для корректной десериализации
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "database.ser";
    
    // Поля теперь приватные для защиты данных (инкапсуляция)
    private List<Person> persons = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();
    
    // Статическое поле для Singleton
    private static DB instance;

    // Приватный конструктор (никто не сможет создать DB через new)
    private DB() {}

    // Метод для получения единственного экземпляра базы
    public static DB getInstance() {
        if (instance == null) {
            instance = load(); // Пытаемся загрузить при первом обращении
            if (instance == null) {
                instance = new DB(); // Если файла нет, создаем пустую базу
                instance.seed(); // Инициализируем начальными данными
            }
        }
        return instance;
    }

    // Сохранение всей базы целиком
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Данные успешно сохранены в " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении базы: " + e.getMessage());
        }
    }

    // Загрузка базы из файла
    private static DB load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (DB) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке базы: " + e.getMessage());
            return null;
        }
    }

    private void seed()
    {
        System.out.println("Файл базы данных не найден. Инициализация начальных данных...");
        Admin admin = new Admin("admin", "admin");
        Manager manager = new Manager("manager", "manager");
        Teacher teacher = new Teacher("teacher", "teacher");
        Student student = new Student("student", "student", 2024, "24B031033");
        Employee employee = new Employee("employee", "employee");
        employee.setResearcherProfile(new Researcher(4, "Computer Science"));
        persons.add(admin);
        persons.add(manager);
        persons.add(teacher);
        persons.add(student);
        persons.add(employee);

        Course course = new Course("CS101", "Introduction to Computer Science", teacher);
        ResearchProject project = new ResearchProject("AI Research", ResearchStatus.InProcess, employee.getResearcherProfile());
        courses.add(course);
        researchProjects.add(project);
        save();
    }

    // Геттеры для списков (чтобы менюшки могли брать данные)
    public List<Person> getPersons() { return persons; }
    public List<Course> getCourses() { return courses; }
    public List<ResearchProject> getResearchProjects() { return researchProjects; }
    public List<Request> getRequests() { return requests; }
}