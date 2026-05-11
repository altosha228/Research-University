import db.DB;
import Models.*;
import UI.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. - First, initializing db
        DB db = DB.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Добро пожаловать в University System ---");


        // 2. - Authentication
        System.out.print("Введите юзернейм: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        Person user = null;
        for (Person p : db.getPersons()) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                user = p;
                break;
            }
        }
        if (user == null) {
            System.out.println("Ошибка: Пользователь не найден или пароль неверен.");
            return;
        }


        // 3. - Giving menu according to user Role.
        System.out.println("Успешная аутентификация! Вы - " + user.getClass().getSimpleName());
        if (user instanceof Teacher)
        {
            // TeacherMenu menu = new TeacherMenu()
            // menu.display()
        }
        else if (user instanceof Student)
        {
            // StudentMenu menu = new StudentMenu()
            // menu.display()
        }
        else if (user instanceof Manager)
        {
            ManagerMenu menu = new ManagerMenu(db, scanner, (Manager) user);
            menu.display();
        }
        else if (user instanceof Employee)
        {
            EmployeeMenu menu = new EmployeeMenu((Employee) user, db, scanner);
            menu.display();
        }
        System.out.println("Спасибо за пользование University System!");
        db.save(); // saveing DB state before terminating process.
        scanner.close();
    }
}
