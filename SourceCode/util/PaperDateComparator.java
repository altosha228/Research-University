package util;
import java.io.*;
import java.util.*;
import Models.*;

public class PaperDateComparator implements Comparator<ResearchPaper> {

    /*
    Default constructor
    */
    public PaperDateComparator() {
    }
    @Override
    public int compare(ResearchPaper rp1, ResearchPaper rp2)
    {
        return rp1.getDate().compareTo(rp2.getDate());
    }
}