package Models;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Manager extends Employee
{
    public Manager(String username, String password){
        super(username, password);
    }


    public void addResearcherToProject(Researcher researcher, ResearchProject researchProject)
    {
        researchProject.addParticipant(researcher);
    }
    public void removeResearcherFromProject(Researcher researcher, ResearchProject researchProject)
    {
        researchProject.removeParticipant(researcher);
    }
    public void addTeacherToCourse(Teacher teacher, Course course)
    {
        teacher.addCourse(course);
        course.addInstructor(teacher);
    }
    public void removeTeacherFromCourse(Teacher teacher, Course course)
    {
        teacher.removeCourse(course);
        course.removeInstructor(teacher);
    }
    public void addStudentToCourse(Student student, Course course)
    {
        course.addStudent(student);
        student.addCourse(course);
    }
    public void removeStudentFromCourse(Student student, Course course)
    {
        course.removeStudent(student);
        student.removeCourse(course);
    }
    public void addStudentSupervisor(Student student, Researcher researcher) throws ValidationException
    {
        try
        {
            student.setSupervisor(researcher);
        } catch (ValidationException e)
        {
            throw e;
        }
        student.setSupervisor(researcher);
    }
    public void removeStudentSupervisor(Student student) throws Exception
    {
        try
        {
            student.setSupervisor(null);
        } catch(Exception e)
        {
            throw e;
        }
    }
}