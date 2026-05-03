import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Researcher {
    private int hIndex;
    private String school;

    private Researcher supervisor;
    private List<ResearchProject> researchProjects;
    private List<ResearchPaper> researchPapers;

    public Researcher(int hIndex, String school) {
        this.hIndex = hIndex;
        this.school = school;
        this.researchProjects = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
    }

    public int getHIndex() {
        return hIndex;
    }

    public String getSchool() {
        return school;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sortedPapers = new ArrayList<>(researchPapers);
        sortedPapers.sort(comparator);

        for (ResearchPaper paper : sortedPapers) {
            System.out.println(paper);
        }
    }

    public int calculateTotalCitations() {
        int total = 0;

        for (ResearchPaper paper : researchPapers) {
            total += paper.getCitations();
        }

        return total;
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