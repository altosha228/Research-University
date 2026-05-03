public class Mark {
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
