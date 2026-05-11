package UI;

import Models.ResearchPaper;

public class ResearcherMenu {
    private Researcher researcher;
    public ResearcherMenu(Researcher researcher){
        this.researcher = researcher;
    }
    public void printResearchPapers(){
        System.out.println("Research Papers: ");
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
