package Models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import db.DB;

public class Admin extends Person {

    public Admin(String username, String password) {
        super(username, password);
    }

    public Person getTopResearcherOfSchool(String name, List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getResearcherProfile() != null)
                .filter(p -> name.equals(p.getResearcherProfile().getSchool()))
                .max(Comparator.comparingInt(p -> p.getResearcherProfile().getHIndex()))
                .orElse(null);
    }

    public Person getTopResearcherOfYear(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getResearcherProfile() != null)
                .max(Comparator.comparingInt(p -> p.getResearcherProfile().calculateTotalCitations()))
                .orElse(null);
    }


}
