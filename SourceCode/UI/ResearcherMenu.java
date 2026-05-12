package UI;
import java.time.LocalDate;
import java.util.*;
import Models.*;
import db.DB;

public class ResearcherMenu {
    private Person person;
    private Researcher researcher;
    private Scanner scanner;
    private DB dbInstance;
    public ResearcherMenu(Researcher researcher, Person person, DB dbInstance, Scanner scanner) {
        this.researcher = researcher;
        this.person = person;
        this.dbInstance = dbInstance;
        this.scanner = scanner;
    }

    public void display(){
        while (true){
            showMenuCommands();

            System.out.print("> ");
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

    public void printResearchProjects(){
        System.out.println("\n-Исследовательские проекты: ");
        if (dbInstance.getResearchProjects().isEmpty()) {
            System.out.println("Проекты не найдены.");
            return;
        }
        for(ResearchProject project : dbInstance.getResearchProjects()){
            System.out.println(project);
        }
    }

    public void enrollToResearchProjectDialog() {
       
        System.out.println("\n--- Запись на исследовательский проект ---");
        printResearchProjects();
        System.out.print("Введите название проекта: ");
        String projectName = scanner.nextLine();

        for (ResearchProject project : dbInstance.getResearchProjects()) {
            if (project.getTopic().equalsIgnoreCase(projectName)) {

                ResearchEnrollRequest request = new ResearchEnrollRequest(project, researcher, person.getUsername(), "Хочу присоединиться к проекту!");
                if (dbInstance.getRequests().contains(request)) {
                    System.out.println("Вы уже отправляли заявку на этот проект.");
                    return;
                }

                dbInstance.getRequests().add(request);

                System.out.println("Заявка успешно отправлена менеджеру.");
                return;
            }
        }
        System.out.println("Проект с таким названием не найден.");
    }

    private void addResearchPaperDialog() {

        System.out.println("\n--- Добавление научной статьи ---");

        System.out.print("Введите название статьи: ");
        String title = scanner.nextLine();

        System.out.print("Введите авторов через запятую: ");
        String authorsInput = scanner.nextLine();

        List<String> authors =
                Arrays.asList(authorsInput.split(","));

        System.out.print("Введите количество страниц: ");
        int pages = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите количество цитирований: ");
        int citations = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите DOI статьи: ");
        String doi = scanner.nextLine();

        ResearchPaper paper = new ResearchPaper(
                title,
                authors,
                pages,
                citations,
                LocalDate.now(),
                doi
        );

        researcher.addResearchPaper(paper);

        System.out.println("Статья успешно добавлена.");
    }

    private void removeResearchPaperDialog() {

        System.out.println("\n--- Удаление научной статьи ---");

        if (researcher.getResearchPapers().isEmpty()) {
            System.out.println("У вас нет статей.");
            return;
        }

        for (int i = 0; i < researcher.getResearchPapers().size(); i++) {

            System.out.println(
                "[" + i + "] " +
                researcher.getResearchPapers().get(i)
            );
        }

        System.out.print("Введите номер статьи: ");

        int index = Integer.parseInt(scanner.nextLine());

        if (index < 0 || index >= researcher.getResearchPapers().size()) {

            System.out.println("Неверный номер.");
            return;
        }

        ResearchPaper removedPaper =
            researcher.getResearchPapers().get(index);

        researcher.removeResearchPaper(removedPaper);

        System.out.println("Статья удалена.");
    }


    public void manageResearchPaperDialog() {
        while (true) {
            System.out.println("\n--- Управление научными статьями ---");
            System.out.println("[0] Показать мои статьи");
            System.out.println("[1] Добавить научную статью");
            System.out.println("[2] Удалить научную статью");
            System.out.println("[q] Назад");

            System.out.print("> ");
            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    printResearchPapers();
                    break;

                case "1":
                    addResearchPaperDialog();
                    break;

                case "2":
                    removeResearchPaperDialog();
                    break;

                case "q":
                    return;

                default:
                    System.out.println("Неизвестная команда!");
            }
        }
    }
}