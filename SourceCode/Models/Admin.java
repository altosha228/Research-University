package Models;

import java.util.Comparator;
import java.util.List;

public class Admin extends Person {

    private List<Person> persons;
    private List<Researcher> researchers;
    private List<Student> students;

    public Admin(String username, String password,
                 List<Person> persons,
                 List<Researcher> researchers,
                 List<Student> students) {
        super(username, password);
        this.persons = persons;
        this.researchers = researchers;
        this.students = students;
    }

    public void printAllPapers(Comparator<ResearchPaper> c) {
        researchers.stream()
                .flatMap(r -> r.getPublishedPapers().stream())
                .sorted(c)
                .forEach(paper -> System.out.println(
                        paper.getName() + " | " + paper.getDate() + " | citations: " + paper.getCitations()
                ));
    }

    public Researcher getTopResearcherOfSchool(String schoolName) {
        return researchers.stream()
                .filter(r -> schoolName.equals(r.getSchool()))
                .max(Comparator.comparingInt(Researcher::getHIndex))
                .orElse(null);
    }

    public Researcher getTopResearcherOfYear() {
        return researchers.stream()
                .max(Comparator.comparingInt(Researcher::calculateTotalCitations))
                .orElse(null);
    }

    public void printMarksReport(String studentId) {
        students.stream()
                .filter(s -> studentId.equals(s.getStudentId()))
                .findFirst()
                .ifPresentOrElse(student -> {
                    System.out.println("Отчёт по оценкам студента: " + studentId);
                    for (Mark mark : student.getMarks()) {
                        System.out.println("  value=" + mark.getValue());
                    }
                }, () -> System.out.println("Студент не найден: " + studentId));
    }

    public void createPerson(List<Person> persons, Person person) {
        persons.add(person);
    }

    public void deletePerson(Person person) {
        persons.remove(person);
    }
}