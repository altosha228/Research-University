package Models;

public class ResearchEnrollRequest extends Request {
    private ResearchProject project;
    private Researcher researcher;

    public ResearchEnrollRequest(ResearchProject project, Researcher researcher) {
        super("Заявка на вступление в исследовательский проект");
        this.project = project;
        this.researcher = researcher;
    }

    public ResearchProject getProject() {
        return project;
    }

    public Researcher getResearcher() {
        return researcher;
    }

    
    public void apply() {
        project.addParticipant(researcher);
        researcher.addResearchProject(project);

        System.out.println("Исследователь успешно добавлен в проект.");
    }

    
    public String toString() {
        return "ResearchEnrollRequest{" +
                "project=" + project.getTopic() +
                ", researcher=" + researcher +
                '}';
    }
    
}
