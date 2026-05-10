package Models;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import db.DB;

public class ResearchProject implements Serializable{
    private static final long serialVersionUID = 1L;

    private String topic;
    private ResearchStatus status;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> projectParticipants;

    public ResearchProject(String topic, Researcher leadResearcher) {
        this.topic = topic;
        this.status = ResearchStatus.InProcess;
        this.publishedPapers = new ArrayList<>();
        this.projectParticipants = new ArrayList<>();
        this.projectParticipants.add(leadResearcher);
    }

    public String getTopic() {
        return topic;
    }

    public ResearchStatus getStatus() {
        return status;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public List<Researcher> getProjectParticipants() {
        return projectParticipants;
    }

    public void addParticipant(Researcher researcher) {
        projectParticipants.add(researcher);
        researcher.addResearchProject(this);
    }

    public void removeParticipant(Researcher researcher)
    {
        projectParticipants.remove(researcher);
        researcher.removeResearchProject(this);
    }

    public void addPublishedPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", status=" + status +
                ", participants=" + projectParticipants.size() +
                ", publishedPapers=" + publishedPapers.size() +
                '}';
    }
}
