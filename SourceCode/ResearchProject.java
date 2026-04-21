import java.io.*;
import java.util.*;

public class ResearchProject implements IPublishable {

    /*
    Default constructor
    */
    public ResearchProject() {
    }


    public String topic;
    public ResearchStatus status;
    public Set<ResearchPaper> publishedPapers;
    public Set<Researcher> ProjectParticipants;

}