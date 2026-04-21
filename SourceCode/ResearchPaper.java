
import java.io.*;
import java.util.*;
import java.time.LocalDate;


public class ResearchPaper implements IPublishable {

    /*
    Default constructor
    */
    public ResearchPaper() {
    }


    public String name;
    public List<String> authors;
    public int pages;
    public int citations;
    public LocalDate date;
    public String DOI;
}