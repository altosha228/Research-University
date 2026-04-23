import java.util.*;

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

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setHIndex(int hIndex) {
        this.hIndex = hIndex;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
    }

    public void addResearchProject(ResearchProject project) {
        if (project != null) {
            researchProjects.add(project);
        }
    }

    public void addResearchPaper(ResearchPaper paper) {
        if (paper != null) {
            researchPapers.add(paper);
        }
    }

    public void printResearchers(Comparator<ResearchPaper> c) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        sorted.sort(c);

        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }
    }

    public int calculateTotalCitations() {
        int total = 0;
        for (ResearchPaper p : researchPapers) {
            total += p.getCitations();
        }
        return total;
    }

    
    public String toString() {
        return "Researcher{" +
                "hIndex=" + hIndex +
                ", school='" + school + '\'' +
                ", supervisor=" + (supervisor != null ? supervisor.getSchool() : "none") +
                '}';
    }
}