import java.util.Scanner;

public class StudentMenu {
    private Student student; // связь -+student с диаграммы

    public StudentMenu(Student student) {
        this.student = student;
    }

    public void printMarks() {
        System.out.println("Вывод оценок для студента: " + student.getUsername());
    }

    public void printCourses() {
        System.out.println("Вывод списка курсов студента: " + student.getUsername());
    }

    public void enrollToCourseDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Запись на курс ---");
        System.out.println("1. Подтвердить запись на новый курс");
        System.out.print("Выберите действие: ");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            Course newCourse = new Course(); 
            student.addCourse(newCourse);   
        }
    }

    // НОВЫЙ МЕТОД ПО ТРЕБОВАНИЮ ТИМЛИДА:
    public void requestSupervisorDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Диалог выбора научного руководителя (Supervisor) ---");
        System.out.println("Доступные исследователи:");
        System.out.println("1. Dr. Darkhan (Machine Learning)");
        System.out.println("2. Dr. Asel (Data Science)");
        System.out.print("Выберите профессора для отправки запроса: ");
        
        int choice = scanner.nextInt();
        if (choice == 1 || choice == 2) {
            System.out.println("Запрос на супервайзерство успешно отправлен Менеджеру на рассмотрение!");
        } else {
            System.out.println("Отменено.");
        }
    }
}   