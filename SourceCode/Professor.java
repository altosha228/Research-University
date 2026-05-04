import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Teacher {

    public Professor(String name, LocalDate dateEmployed) {
        super(name, dateEmployed);
    }

    @Override
    public String toString() {
        return "Professor{name='" + name + "', dateEmployed=" + dateEmployed +
               ", courses=" + getCourses().size() + "}";
    }
}