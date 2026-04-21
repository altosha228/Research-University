import java.io.*;
import java.util.*;


public class PaperCitationsComparator implements Comparator<ResearchPaper> {

    /*
    Default constructor
    */
    public PaperCitationsComparator() {
    }
    @Override
    public int compare(ResearchPaper rp1, ResearchPaper rp2)
    {
        return Integer.compare(rp1.citations, rp2.citations);
    }
}