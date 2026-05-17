package UI;

import db.DB;
import util.constants;
import Models.*;

import java.util.Comparator;
import java.util.Objects;
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
                    printTopResearcherOfSchoolDialog();
                    showMenuCommands();
                    break;
                case "2":
                    printTopResearcherOfYear();
                    showMenuCommands();
                    break;
                case "3":
                    printAllPapers(Comparator.comparing(ResearchPaper::getDate).reversed());
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
        System.out.println(constants.ANSI_YELLOW + "[1]" + constants.ANSI_RESET + " - Лучший исследователь школы");
        System.out.println(constants.ANSI_YELLOW + "[2]" + constants.ANSI_RESET + " - Лучший исследователь года");
        System.out.println(constants.ANSI_YELLOW + "[3]" + constants.ANSI_RESET + " - Показать все исследования");
        System.out.println(constants.ANSI_YELLOW + "[q]" + constants.ANSI_RESET + " - Выйти");
    }

    public void printAllPapers(Comparator<ResearchPaper> c) {
        DB.getInstance().getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null)
                .<ResearchPaper>flatMap(p -> p.getResearcherProfile().getResearchPapers().stream())
                .sorted(c)
                .forEach(paper -> System.out.println(
                        paper.getName() + " | " + paper.getDate() + " | citations: " + paper.getCitations()));
    }

    public void printAllUsers(Comparator<Person> c) {
        System.out.println("\n" + "\u001B[33m" + "--- 👤 Список всех пользователей ---" + "\u001B[0m");

        dbInstance.getPersons().stream()
                // Явно указываем тип объектов в стриме, чтобы методы были видны
                .filter(Objects::nonNull)
                .sorted(c)
                .forEach((Person p) -> {
                    String role = p.getClass().getSimpleName();
                    System.out.printf("%-15s | Role: %-10s%n", p.getUsername(), role);
                });
    }

    public void printAllUsers() {
        printAllUsers(Comparator.comparing(Person::getUsername));
    }

    private void printTopResearcherOfSchoolDialog() {
        System.out.println("Введите название школы: ");
        String schoolName = sc.nextLine();
        Person top = admin.getTopResearcherOfSchool(schoolName, dbInstance.getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null).toList());
        if (top == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
        } else {
            System.out.println(constants.ANSI_GREEN + "Лучший исследователь школы " + schoolName
                    + ": " + top.getUsername()
                    + " | hIndex: " + top.getResearcherProfile().getHIndex()
                    + constants.ANSI_RESET);
        }
    }

    private void printTopResearcherOfYear() {
        Person top = admin.getTopResearcherOfYear(dbInstance.getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null).toList());
        if (top == null) {
            System.out.println(constants.ANSI_RED + "Исследователь не найден!" + constants.ANSI_RESET);
        } else {
            System.out.println(constants.ANSI_GREEN + "Лучший исследователь года: "
                    + top.getUsername()
                    + " | citations: " + top.getResearcherProfile().calculateTotalCitations()
                    + constants.ANSI_RESET);
        }
    }
}
