import java.util.Comparator;
import java.util.List;

public class Admin {

    private List<Researcher> researchers;
    private List<Student> students;

    public Admin(List<Researcher> researchers, List<Student> students) {
        this.researchers = researchers;
        this.students = students;
    }

    /**
     * Prints all research papers across all researchers, sorted by the given comparator.
     * Signature from diagram: printAllPapers(c: Comparator): void
     * Admin fetches papers internally — no list passed as parameter.
     */
    public void printAllPapers(Comparator<ResearchPaper> c) {
        researchers.stream()
                .flatMap(r -> r.getResearchPapers().stream())
                .sorted(c)
                .forEach(p -> System.out.println(p.getName()
                        + " | " + p.getDate()
                        + " | pages: " + p.getPages()
                        + " | citations: " + p.getCitations()));
    }

    /**
     * Returns the researcher with the highest hIndex from the given school.
     * Uses hIndex (not citations) — hIndex is the researcher's academic rank metric.
     * Signature from diagram: getTopResearcherOfSchool(schoolName: String): Researcher
     */
    public Researcher getTopResearcherOfSchool(String schoolName) {
        return researchers.stream()
                .filter(r -> schoolName.equals(r.getSchool()))
                .max(Comparator.comparingInt(Researcher::getHIndex))
                .orElse(null);
    }

    /**
     * Returns the researcher with the highest hIndex across all researchers.
     * Signature from diagram: getTopResearcherOfYear(): Researcher
     */
    public Researcher getTopResearcherOfYear() {
        return researchers.stream()
                .max(Comparator.comparingInt(Researcher::getHIndex))
                .orElse(null);
    }

    /**
     * Prints all marks for the student with the given ID.
     * Signature from diagram: printMarksReport(studentId: String): void
     * Fixed: no null passed to getMarksByCourse — prints all marks directly.
     */
    public void printMarksReport(String studentId) {
        students.stream()
                .filter(s -> studentId.equals(s.studentId))
                .findFirst()
                .ifPresentOrElse(student -> {
                    System.out.println("=== Marks report for student: " + studentId + " ===");
                    student.marks.forEach(m ->
                            System.out.println("  Lesson: " + m.getLesson().getTopic()
                                    + " | Value: " + m.getValue()));
                }, () -> System.out.println("Student not found: " + studentId));
    }
}
