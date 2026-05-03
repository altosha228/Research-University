import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ResearchPaper implements IPublishable {
    private String name;
    private List<String> authors;
    private int pages;
    private int citations;
    private LocalDate date;
    private String DOI;

    public ResearchPaper(String name, List<String> authors, int pages, int citations, LocalDate date, String DOI) {
        this.name = name;
        this.authors = authors;
        this.pages = pages;
        this.citations = citations;
        this.date = date;
        this.DOI = DOI;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthors() {
        return authors;
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

  
    public void publish() {
        System.out.println("Research paper published: " + name);
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

  
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(DOI, that.DOI);
    }

    
    public int hashCode() {
        return Objects.hash(DOI);
    }
}