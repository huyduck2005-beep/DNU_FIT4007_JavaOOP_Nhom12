package model;

public class Certificate {
    private String id;
    private String studentId;
    private String courseId;
    private String issueDate;
    private double finalScore;

    public Certificate(String id, String studentId, String courseId, String issueDate, double finalScore) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = issueDate;
        this.finalScore = finalScore;
    }

    public String getId() { return id; }
    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public double getFinalScore() {
        return finalScore;
    }

    @Override
    public String toString() {
        return "Certificate{" + "id='" + id + '\'' + ", studentId='" + studentId + '\'' + ", courseId='" + courseId + '\'' + ", issueDate='" + issueDate + '\'' + ", finalScore=" + finalScore + '}';
    }
}