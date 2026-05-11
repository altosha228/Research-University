package UI;

import java.util.*;
import Models.*;
import db.DB;

public class EmployeeMenu {
    private Employee employee;
    private ResearcherMenu researcherMenu;
    private DB dbInstance;
    private Scanner scanner;
    public EmployeeMenu(Employee employee, DB dbInstance, Scanner scanner) {
        this.employee = employee;
        this.dbInstance = dbInstance;
        this.scanner = scanner;
        if (employee.getResearcherProfile() != null) {
                researcherMenu = new ResearcherMenu(employee.getResearcherProfile(), dbInstance, scanner);
        }
    }
    public void display() {
        while (true) {
            showCommands();
            String choice = scanner.nextLine();
            if (researcherMenu != null) {
                if (choice.equals("r")) {
                    researcherMenu.display();
                }
            }
            switch (choice) {
                case "q": return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
    
            }
        }
    }
    public void showCommands() {
        System.out.println("\n--- Меню сотрудника ---");
        if (researcherMenu != null) {
            System.out.println("[r] - Войти в меню исследователя");
        }
        System.out.println("[q] - Выйти из меню сотрудника");
    }
}
