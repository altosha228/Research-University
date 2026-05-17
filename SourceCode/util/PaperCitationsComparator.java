package util;
import java.io.*;
import java.util.*;
import Models.*;

public class PaperCitationsComparator implements Comparator<ResearchPaper> {

    /*
    Default constructor
    */
    public PaperCitationsComparator() {
    }
    @Override
    public int compare(ResearchPaper rp1, ResearchPaper rp2)
    {
        return Integer.compare(rp1.getCitations(), rp2.getCitations());
    }
}