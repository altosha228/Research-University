import java.io.*;
import java.util.*;


public class Student extends Person {

    /*
    Default constructor
    */
    public Student() {
    }

    public int year;
    public String studentId;
    private Researcher supervisor;
    public Set<Mark> marks;
    public Researcher researcher;

    public void setSupervisor(Researcher supervisor) {
        // TODO implement here
    }

    public List<Mark> getMarksByCourse(Course course) {
        // TODO implement here
        return null;
    }

}