package util;
import java.io.*;
import java.util.*;
import Models.*;

public class PaperPagesComparator implements Comparator<ResearchPaper> {

    /*
    Default constructor
    */
    public PaperPagesComparator() {
    }
    @Override
    public int compare(ResearchPaper rp1, ResearchPaper rp2)
    {
        return Integer.compare(rp1.getPages(), rp2.getPages());
    }
}