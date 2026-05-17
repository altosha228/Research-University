package Models;

import util.constants;
import java.util.*;

public class ResearchEnrollRequest extends Request {
    private ResearchProject project;
    private Researcher researcher;

    public ResearchEnrollRequest(ResearchProject project, Researcher researcher, String username, String message) {
        super(String.format("Исследователь %s хочет вступить в проект %s", username, project.getTopic()), message);
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
        System.out.println(constants.ANSI_GREEN + "Исследователь успешно добавлен в проект." + constants.ANSI_RESET);
    }

    
    public String toString() {
        return "ResearchEnrollRequest{" +
                "project=" + project.getTopic() +
                ", researcher=" + researcher +
                '}';
    }
    
}
