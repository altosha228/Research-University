package Models;
import java.io.Serializable;
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int value;
    private Lesson lesson;

    public Mark(int value, Lesson lesson) {
        this.value = value;
        this.lesson = lesson;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
