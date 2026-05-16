package UI;

import java.util.*;
import Models.*;
import db.DB;
import util.constants;

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
                researcherMenu = new ResearcherMenu(employee.getResearcherProfile(), (Person) employee, dbInstance, scanner);
        }
    }
    public void display() {
        while (true)
        {
            showCommands();
            System.out.print("> ");
            String choice = scanner.nextLine();
            if (researcherMenu != null) {
                if (choice.equals("r")) {
                    researcherMenu.display();
                    continue;
                }
            }
            switch (choice) {
                case "q": return;
                default:
                    System.out.println(constants.ANSI_RED + "Неизвестная команда!" + constants.ANSI_RESET);
            }
        }
    }
    public void showCommands() {
        System.out.println("\n--- Меню сотрудника ---");
        if (researcherMenu != null) {
            System.out.println( constants.ANSI_YELLOW + "[r]" + constants.ANSI_RESET + " - Войти в меню исследователя");
        }
        System.out.println( constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Выйти из меню сотрудника");
    }
}
