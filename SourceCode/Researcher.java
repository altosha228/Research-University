
import java.io.*;
import java.util.*;

public class Researcher {
    private int hIndex;
    private String school;

    
    public Researcher(int hIndex, String school) {
        this.hIndex = hIndex;
        this.school = school;
        this.researchProjects = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
    }
    public int hIndex;
    public String school;
    public Set<ResearchProject> researchProject;
    public Set<ResearchPaper> researchPaper;


    public void PrintResearches(Comparator c) {
        // TODO implement here
    }


    public int calculateTotalCitations() {
        // TODO implement here
        return 0;
    }
}