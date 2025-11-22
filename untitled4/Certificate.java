import java.time.LocalDate;

// File: Certificate.java
public class Certificate extends AbstractEntity {
    private String learnerId;
    private String courseId;
    private String uniqueCode; // Mã chứng chỉ duy nhất
    private double finalGrade;
    private LocalDate issueDate;

    public Certificate(String id, String learnerId, String courseId, String uniqueCode, double finalGrade, LocalDate issueDate) {
        super(id);
        this.learnerId = learnerId;
        this.courseId = courseId;
        this.uniqueCode = uniqueCode;
        this.finalGrade = finalGrade;
        this.issueDate = issueDate;
    }

    public String getLearnerId() { return learnerId; }
    public String getCourseId() { return courseId; }
    public String getUniqueCode() { return uniqueCode; }
    public double getFinalGrade() { return finalGrade; }
    public LocalDate getIssueDate() { return issueDate; }

    @Override
    public void saveToFile() { DataManager.saveToFile(this, "certificates.csv"); }
    @Override
    public void loadFromFile() { /* Load logic... */ }
    @Override
    public String toString() {
        return id + "," + learnerId + "," + courseId + "," + uniqueCode + "," + finalGrade + "," + issueDate;
    }
}