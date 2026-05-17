package Models;
import java.time.LocalDate;

public class Employee extends Person {
    public LocalDate dateEmployed;

    public Employee(String username, String password) {
        super(username, password);
        this.dateEmployed = LocalDate.now();
    }

    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(LocalDate dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{username='" + this.getUsername() + "', dateEmployed=" + dateEmployed + "}";
    }
}