package Models;
import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Researcher researcherProfile; // Optional: only for researchers

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Researcher getResearcherProfile() {
        return researcherProfile;
    }

    public void setResearcherProfile(Researcher researcherProfile) {
        this.researcherProfile = researcherProfile;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{username='" + username + "'}";
    }
}