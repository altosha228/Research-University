import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResearchPaper implements IPublishable {
    private String name;
    private List<String> authors;
    private int pages;
    private int citations;
    private LocalDate date;
    private String DOI;

    public ResearchPaper(String name, List<String> authors, int pages, int citations, LocalDate date, String DOI) {
        this.name = name;
        this.authors = new ArrayList<>(authors);
        this.pages = pages;
        this.citations = citations;
        this.date = date;
        this.DOI = DOI;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    public int getPages() {
        return pages;
    }

    public int getCitations() {
        return citations;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDOI() {
        return DOI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(List<String> authors) {
        this.authors = new ArrayList<>(authors);
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    
    public void publish() {
        System.out.println("Research paper \"" + name + "\" has been published.");
    }

   
    public String toString() {
        return "ResearchPaper{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", pages=" + pages +
                ", citations=" + citations +
                ", date=" + date +
                ", DOI='" + DOI + '\'' +
                '}';
    }
}