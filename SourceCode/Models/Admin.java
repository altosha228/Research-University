package Models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Admin extends Person {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void printAllPapers(Comparator<ResearchPaper> c) {
        DB.getInstance().getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null)
                .flatMap(p -> p.getResearcherProfile().getPublishedPapers().stream())
                .sorted(c)
                .forEach(paper -> System.out.println(
                        paper.getName() + " | " + paper.getDate() + " | citations: " + paper.getCitations()
                ));
    }

    public Researcher getTopResearcherOfSchool(String name) {
        return DB.getInstance().getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null)
                .map(p -> p.getResearcherProfile())
                .filter(r -> name.equals(r.getSchool()))
                .max(Comparator.comparingInt(Researcher::getHIndex))
                .orElse(null);
    }

    public Researcher getTopResearcherOfYear() {
        return DB.getInstance().getPersons().stream()
                .filter(p -> p.getResearcherProfile() != null)
                .map(p -> p.getResearcherProfile())
                .max(Comparator.comparingInt(Researcher::calculateTotalCitations))
                .orElse(null);
    }

    public void printMarksReport(String studentId) {
        DB.getInstance().getPersons().stream()
                .filter(p -> p instanceof Student)
                .map(p -> (Student) p)
                .filter(s -> studentId.equals(s.getStudentId()))
                .findFirst()
                .ifPresentOrElse(student -> {
                    System.out.println("Отчёт по оценкам студента: " + studentId);
                    for (Mark mark : student.getMarks()) {
                        System.out.println("  value=" + mark.getValue());
                    }
                }, () -> System.out.println("Студент не найден: " + studentId));
    }
}
