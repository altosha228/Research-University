import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private ResearchStatus status;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> projectParticipants;

    public ResearchProject(String topic, ResearchStatus status) {
        this.topic = topic;
        this.status = status;
        this.publishedPapers = new ArrayList<>();
        this.projectParticipants = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public ResearchStatus getStatus() {
        return status;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return new ArrayList<>(publishedPapers);
    }

    public List<Researcher> getParticipants()
    {
        return new ArrayList<>(projectParticipants);
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setStatus(ResearchStatus status) {
        this.status = status;
    }

    public void addPublishedPaper(ResearchPaper paper) {
        if (paper != null) {
            publishedPapers.add(paper);
        }
    }

    public void addParticipant(Researcher researcher)
    {
        if (researcher != null) {
            projectParticipants.add(researcher);
        }
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", status=" + status +
                ", publishedPapers=" + publishedPapers +
                '}';
    }
}