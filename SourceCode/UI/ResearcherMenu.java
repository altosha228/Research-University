package UI;

import Models.ResearchPaper;

public class ResearcherMenu {
    private Researcher researcher;
    private Scanner scanner;
    public ResearcherMenu(Researcher researcher){
        this.researcher = researcher;
        this.scanner = new Scanner(System.in);
    }

    public void display(){
        while (true){
            showMenuCommands();

            System.out.println("Choose command: ");
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
                    return;

                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    private void showMenuCommands() {

        System.out.println("\n--- Researcher Menu ---");
        System.out.println("[0] Print research papers");
        System.out.println("[1] Enroll to research project");
        System.out.println("[2] Manage research papers");
        System.out.println("[q] Exit");
    }


    public void printResearchPapers(){
        System.out.println("Research Papers: ");
        if (researcher.getResearchPapers().isEmpty()) {
            System.out.println("No papers found.");
            return;
        }
        for(ResearchPaper paper : researcher.getResearchpapers()){
            System.out.println(paper);
        }
    }

    public void enrollToResearchProjectDialog() {
        System.out.println("Enroll to research project...");
    }

    public void manageResearchPaperDialog() {
        System.out.println("Manage research papers...");
    }    

}
