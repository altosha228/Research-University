import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Researcher {
    private int hIndex;
    private String school;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Researcher(int hIndex, String school) {
        this.hIndex = hIndex;
        this.school = school;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public int getHIndex() {
        return hIndex;
    }

    public String getSchool() {
        return school;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    public int calculateTotalCitations() {
        int total = 0;
        for (ResearchPaper paper : researchPapers) {
            total += paper.getCitations();
        }
        return total;
    }

    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sortedPapers = new ArrayList<>(researchPapers);
        sortedPapers.sort(comparator);

        for (ResearchPaper paper : sortedPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public String toString() {
        return "Researcher{" +
                "hIndex=" + hIndex +
                ", school='" + school + '\'' +
                ", totalCitations=" + calculateTotalCitations() +
                '}';
    }
}