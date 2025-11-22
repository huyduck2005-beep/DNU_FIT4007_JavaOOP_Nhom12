import java.time.LocalDate;

public class Enrollment extends AbstractEntity {
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;

    public Enrollment(String id, String studentId, String courseId, LocalDate enrollmentDate) {
        super(id);
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters và setters
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }

    @Override
    public void saveToFile() {
        DataManager.saveToFile(this, "enrollments.txt");
    }

    @Override
    public void loadFromFile() {
        DataManager.loadFromFile(this, "enrollments.txt");
    }

    @Override
    public String toString() {
        return id + "," + studentId + "," + courseId + "," + enrollmentDate;
    }
}