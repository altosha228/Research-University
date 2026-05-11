package UI;

import java.util.*;
import Models.*;
import db.DB;

public class ResearcherMenu {
    private Researcher researcher;
    private Scanner scanner;
    private DB dbInstance;
    public ResearcherMenu(Researcher researcher, DB dbInstance, Scanner scanner) {
        this.researcher = researcher;
        this.dbInstance = dbInstance;
        this.scanner = scanner;
    }

    public void display(){
        while (true){
            showMenuCommands();

            System.out.println("Введите команду: ");
            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    printResearchPapers();
                    break;
                case "1":
                    enrollToResearchProjectDialog();
                    break;

                case "2":
                    manageResearchPaperDialog();
                    break;

                case "q":
                    System.out.println("Выход из меню исследователя");
                    return;

                default:
                    System.out.println("Неизвестная команда!");
            }
        }
    }

    private void showMenuCommands() {

        System.out.println("\n---  Меню исследователя  ---");
        System.out.println("[0] Показать научные статьи");
        System.out.println("[1] Записаться на исследовательский проект");
        System.out.println("[2] Управление научными статьями");
        System.out.println("[q] Выход");
    }


    public void printResearchPapers(){
        System.out.println("\n-Научные статьи: ");
        if (researcher.getResearchPapers().isEmpty()) {
            System.out.println("Статьи не найдены.");
            return;
        }
        for(ResearchPaper paper : researcher.getResearchPapers()){
            System.out.println(paper);
        }
    }

    public void enrollToResearchProjectDialog() {
        System.out.println("\n-Запись на исследовательский проект");
    }

    public void manageResearchPaperDialog() {
        System.out.println("\n-Управление научными статьями.");
    }    

}
