import java.time.LocalDate;

public class Employee extends Person {
    public LocalDate dateEmployed;

    public Employee(String name, LocalDate dateEmployed) {
        super(name);
        this.dateEmployed = dateEmployed;
    }

    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(LocalDate dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', dateEmployed=" + dateEmployed + "}";
    }
}