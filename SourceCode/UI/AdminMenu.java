package UI;

import db.DB;
import util.constants;
import Models.*;

import java.util.Comparator;
import java.util.Scanner;

public class AdminMenu {
    private DB dbInstance;
    private Scanner sc;
    private Admin admin;

    public AdminMenu(DB dbInstance, Scanner sc, Admin admin) {
        this.dbInstance = dbInstance;
        this.sc = sc;
        this.admin = admin;
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
                    printAllUsers();
                    showMenuCommands();
                    break;
                case "1":
                    printMarksReportDialog();
                    showMenuCommands();
                    break;
                case "2":
                    printTopResearcherOfSchoolDialog();
                    showMenuCommands();
                    break;
                case "3":
                    printTopResearcherOfYear();
                    showMenuCommands();
                    break;
                default:
                    System.out.printf("Неизвестная команда: %s%n", input.trim());
                    break;
            }
        }
    }

    private void showMenuCommands() {
        System.out.println("--- Меню администратора ---");
        System.out.println("Команды:");
        System.out.println(constants.ANSI_YELLOW + "[0]" + constants.ANSI_RESET + " - Показать всех пользователей");
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Отчёт по оценкам студента");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Лучший исследователь школы");
        System.out.println(constants.ANSI_YELLOW + "[3]" + constants.ANSI_RESET + " - Лучший исследователь года");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Выйти");
    }

    public void printAllUsers(Comparator c) {
        System.out.println("--- Список всех пользователей ---");
        dbInstance.getPersons().stream()
                .sorted(c)
                .forEach(p -> System.out.println("- " + p.getUsername() + " | " + p.getClass().getSimpleName()));
    }

    public void printAllUsers() {
        printAllUsers(Comparator.comparing(Person::getUsername));
    }

    public void printMarksReport(String studentId) {
        admin.printMarksReport(studentId);
    }

    private void printMarksReportDialog() {
        System.out.println("Введите ID студента: ");
        String studentId = sc.nextLine();
        printMarksReport(studentId);
    }

    private void printTopResearcherOfSchoolDialog() {
        System.out.println("Введите название школы: ");
        String schoolName = sc.nextLine();
        Researcher top = admin.getTopResearcherOfSchool(schoolName);
        if (top == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
        } else {
            System.out.println(constants.ANSI_GREEN + "Лучший исследователь школы " + schoolName
                    + ": " + top.getUsername()
                    + " | hIndex: " + top.getHIndex()
                    + constants.ANSI_RESET);
        }
    }

    private void printTopResearcherOfYear() {
        Researcher top = admin.getTopResearcherOfYear();
        if (top == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
        } else {
            System.out.println(constants.ANSI_GREEN + "Лучший исследователь года: "
                    + top.getUsername()
                    + " | citations: " + top.calculateTotalCitations()
                    + constants.ANSI_RESET);
        }
    }
}
