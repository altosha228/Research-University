
import java.io.*;
import java.util.*;

public class PaperDateComparator implements Comparator<ResearchPaper> {

    /*
    Default constructor
    */
    public PaperDateComparator() {
    }
    @Override
    public int compare(ResearchPaper rp1, ResearchPaper rp2)
    {
        return rp1.date.compareTo(rp2.date);
    }
}